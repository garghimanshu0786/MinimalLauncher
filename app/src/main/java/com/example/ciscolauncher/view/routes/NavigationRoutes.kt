package com.example.ciscolauncher.view.routes

sealed class NavigationRoutes(val screen: String) {

	object HomeScreen : NavigationRoutes("home-screen")

	object SettingsScreen : NavigationRoutes("settings-screen")
}