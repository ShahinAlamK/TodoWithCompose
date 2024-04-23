package com.example.todo.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.todo.R
import com.example.todo.components.Buttons
import com.example.todo.components.CustomField
import com.example.todo.data.viewmodel.AuthVideModel
import com.example.todo.navGraph.RouteItem
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(nav: NavController, vm: AuthVideModel = hiltViewModel()) {

    val auth = vm.res.value
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var usernameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var passwordsText by remember { mutableStateOf("") }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight().blur(8.dp),
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "")


        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Spacer(modifier = Modifier.height(100.dp))

                Text(text = "Create a Account", style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(90.dp))

                //Username
                CustomField(
                    value = usernameText,
                    onValueChange = { usernameText = it },
                    placeholder = "Username",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = ""
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))

                //Email Input
                CustomField(
                    value = emailText,
                    onValueChange = { emailText = it },
                    placeholder = "Enter Email",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = ""
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                //Passwords Input
                CustomField(
                    value = passwordsText,
                    onValueChange = { passwordsText = it },
                    placeholder = "Passwords",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = ""
                        )
                    }
                )

                Spacer(modifier = Modifier.height(70.dp))


                if (auth.loading) {
                    CircularProgressIndicator()
                } else {
                    Buttons(
                        label = "Register",
                        onClick = {
                            scope.launch {
                                vm.createWithEmail(usernameText, emailText, passwordsText)
                            }
                        }
                    )
                }
                if (auth.data){
                    nav.navigate(RouteItem.Home.route)
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}