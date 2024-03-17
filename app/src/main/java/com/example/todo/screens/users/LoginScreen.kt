package com.example.todo.screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.components.Buttons
import com.example.todo.components.CustomField
import com.example.todo.navGraph.RouteItem


@Composable
fun LoginScreen(nav: NavController) {

    var emailText by remember { mutableStateOf("") }
    var passwordsText by remember { mutableStateOf("") }


    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item{
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
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = ""
                    )
                }
            )

            Spacer(modifier = Modifier.height(70.dp))

            Buttons(
                label = "Login",
                onClick = {nav.navigate(RouteItem.Home.route)}
            )

            Spacer(modifier = Modifier.height(40.dp))
            Buttons(
                isOutline = true,
                label = "Create a account",
                onClick = {nav.navigate(RouteItem.Register.route)}
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

