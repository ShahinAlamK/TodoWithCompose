package com.example.todo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.todo.R
import com.example.todo.components.DeleteTodo
import com.example.todo.components.NewTodo
import com.example.todo.components.RoundIcon
import com.example.todo.components.TodoCard
import com.example.todo.navGraph.RouteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavController) {

    val isDialog = remember { mutableStateOf(false) }
    val isDelete = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }


    NewTodo(isOpen = isDialog)
    DeleteTodo(isDelete = isDelete)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = { Text(text = stringResource(id = R.string.app_name)) },

                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background)
                            .clickable {nav.navigate(RouteItem.Profile.route) }
                    ) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            model = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fGF2YXRhcnxlbnwwfHwwfHx8MA%3D%3D",
                            contentDescription = "")
                    }
                },

                actions = {

                    RoundIcon(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = ""
                            )
                        },
                        onClick = { isDialog.value = true }
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    RoundIcon(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notifications),
                                contentDescription = ""
                            )
                        },
                        onClick = { /*TODO*/ }
                    )

                    Spacer(modifier = Modifier.size(10.dp))
                }
            )
        },

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            TodoCard(
                delete = { isDelete.value = true }
            )

        }
    }
}





