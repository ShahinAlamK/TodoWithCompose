package com.example.todo.screens.users

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.components.Buttons
import com.example.todo.components.CustomField
import com.example.todo.data.viewmodel.AuthVideModel
import com.example.todo.navGraph.RouteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(nav: NavController, authVideModel: AuthVideModel = hiltViewModel()) {

    val auth = authVideModel.res.value

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var emailText by remember { mutableStateOf("") }
    var passwordsText by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .blur(8.dp),
            painter = painterResource(id = R.drawable.bg),
            contentDescription = ""
        )

        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Spacer(modifier = Modifier.height(100.dp))

                Text(text = "Login", style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(90.dp))

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
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = ""
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(id = if (passwordVisible) R.drawable.visibility_off else R.drawable.visibility),
                                contentDescription = ""
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(70.dp))
                if (auth.loading) CircularProgressIndicator() else
                Buttons(
                    label = "Login",
                    onClick = {
                        scope.launch(Dispatchers.Main) {
                            authVideModel.loginWithEmail(
                                email = emailText.trim(),
                                passwords = passwordsText.trim()
                            )
                        }

                       // nav.navigate(RouteItem.Home.route)
                    }
                )
                if (auth.data){nav.navigate(RouteItem.Home.route)}

                Spacer(modifier = Modifier.height(40.dp))

                Buttons(
                    isOutline = true,
                    label = "Create a account",
                    onClick = {
                        nav.navigate(RouteItem.Register.route)
                    }
                )

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }

}

