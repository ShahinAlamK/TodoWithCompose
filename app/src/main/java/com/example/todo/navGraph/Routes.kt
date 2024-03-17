package com.example.todo.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.screens.HomeScreen
import com.example.todo.screens.users.LoginScreen
import com.example.todo.screens.users.ProfileScreen
import com.example.todo.screens.users.RegisterScreen

@Composable
fun Routes(navHost: NavHostController) {

    NavHost(navController = navHost, startDestination = RouteItem.Login.route) {
        composable(RouteItem.Login.route) { LoginScreen(nav = navHost) }
        composable(RouteItem.Register.route) { RegisterScreen(nav = navHost) }
        composable(RouteItem.Home.route) { HomeScreen(nav = navHost) }
        composable(RouteItem.Profile.route) { ProfileScreen(nav = navHost) }
    }
}