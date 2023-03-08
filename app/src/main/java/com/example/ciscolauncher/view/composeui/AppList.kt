package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ciscolauncher.models.AppInfo
import com.example.ciscolauncher.viewmodels.LauncherViewModel

@Composable
fun AppList(
	filteredApps: List<AppInfo>,
	showHiddenApps: Boolean,
	recentApps: List<AppInfo>,
	viewModel: LauncherViewModel,
	openSettings: () -> Unit
) {
	val expandedApp = remember { mutableStateOf("") }

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.padding(top = 15.dp, start = 15.dp, end = 15.dp)
	) {
		SettingsView(openSettings)

		HiddenAppsWarningView(showHiddenApps, filteredApps, expandedApp)

		Loader(filteredApps)

		RecentAppsView(recentApps, viewModel, expandedApp)

		AllAppsView(filteredApps, viewModel, expandedApp)
	}
}

