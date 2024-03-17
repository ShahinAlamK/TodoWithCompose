package com.example.todo.navGraph

sealed class RouteItem(val route:String) {
    data object Home:RouteItem("home_screen")
    data object Profile:RouteItem("profile_screen")
}