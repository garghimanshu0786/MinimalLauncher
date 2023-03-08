package com.example.ciscolauncher

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ciscolauncher.ui.theme.CiscoLauncherTheme
import com.example.ciscolauncher.view.composeui.LauncherScreen
import com.example.ciscolauncher.view.composeui.SettingsScreen
import com.example.ciscolauncher.view.routes.NavigationRoutes
import com.example.ciscolauncher.viewmodels.LauncherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			CiscoLauncherTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					val navController = rememberNavController()
					val viewModel = hiltViewModel<LauncherViewModel>()
					NavHost(navController, startDestination = NavigationRoutes.HomeScreen.screen) {
						composable(route = NavigationRoutes.HomeScreen.screen) {
							LauncherScreen(
								{ navController.navigate(NavigationRoutes.SettingsScreen.screen) },
								viewModel
							)
						}
						composable(route = NavigationRoutes.SettingsScreen.screen) {
							SettingsScreen(viewModel, navController::navigateUp)
						}
					}
				}
			}
		}
	}
}