package com.example.implementfailedscreen.ui.screens

sealed class DefaultNavigationScreens(val route: String) {
    object Failed : DefaultNavigationScreens(route = "failed")
    object Server : DefaultNavigationScreens(route = "server")
}