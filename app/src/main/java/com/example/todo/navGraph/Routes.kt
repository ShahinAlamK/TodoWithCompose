package com.example.todo.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.screens.HomeScreen
import com.example.todo.screens.ProfileScreen

@Composable
fun Routes(navHost: NavHostController) {

    NavHost(navController = navHost, startDestination = RouteItem.Home.route) {
        composable(RouteItem.Home.route){ HomeScreen(nav = navHost)}
        composable(RouteItem.Profile.route){ ProfileScreen(nav = navHost) }
    }
}