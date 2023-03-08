package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ciscolauncher.viewmodels.LauncherViewModel

@Composable
fun LauncherScreen(
	openSettings: () -> Unit,
	viewModel: LauncherViewModel,
) {
	Column {
		val uiState by viewModel.uiState.collectAsState()
		SearchBar(uiState.searchQuery, viewModel)
		AppList(
			uiState.filteredApps,
			uiState.showHiddenApps,
			uiState.recentApps,
			viewModel,
			openSettings
		)
	}
}

