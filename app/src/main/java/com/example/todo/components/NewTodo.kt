package com.example.todo.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.R
import com.example.todo.data.models.TodoModel
import com.example.todo.data.utils.AppResponse
import com.example.todo.data.viewmodel.TodoState
import com.example.todo.data.viewmodel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NewTodo(isOpen: MutableState<Boolean>,todoViewModel: TodoViewModel = hiltViewModel()) {

    val colorList = listOf(Color(0xFF1F90D3), Color(0xFFF04712), Color(0xFFEC05AF), Color(0xFF31DD47))
    var selectColor by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var todo by remember { mutableStateOf("") }
    var des by remember { mutableStateOf("") }

    if (isOpen.value)
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            textContentColor = MaterialTheme.colorScheme.onBackground,
            tonalElevation = 6.dp,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = {},
            confirmButton = { /*TODO*/ },

            title = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.create))

                    RoundIcon(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clear),
                                contentDescription = ""
                            )
                        },
                        onClick = { isOpen.value = false }
                    )
                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        value = todo,
                        onValueChange = {todo=it},
                        placeholder = {
                            Text(
                                text = "Enter Title",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        value = des,
                        onValueChange = {des = it},
                        placeholder = {
                            Text(
                                text = "Description",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(colorList.size) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(colorList[it])
                                    .padding(10.dp)
                                    .clickable { selectColor = it }
                            ) {
                                if (selectColor == it) {
                                    Icon(
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        painter = painterResource(id = R.drawable.ic_check),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Buttons(label = "Create", onClick = {
                        scope.launch(Dispatchers.Main) {
                            todoViewModel.insertTodo(
                                TodoModel(
                                    id = "",
                                    todo = todo,
                                    description = des,
                                    color = selectColor,
                                    date = ""
                                )
                            ).collect{
                                when(it){
                                    is AppResponse.Loading ->{isLoading = true}
                                    is AppResponse.Success ->{
                                        todoViewModel.fetchTodo()
                                        isLoading = false}
                                    is AppResponse.Failure ->{
                                        Toast.makeText(context, it.msg.toString(), Toast.LENGTH_SHORT)
                                            .show()
                                        isLoading= false}
                                }
                            }
                        }
                    })
                }
            }
        )
}