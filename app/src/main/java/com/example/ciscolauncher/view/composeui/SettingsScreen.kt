package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ciscolauncher.R
import com.example.ciscolauncher.viewmodels.LauncherViewModel

@Composable
fun SettingsScreen(
	viewModel: LauncherViewModel,
	navigateBack: () -> Unit,
) {
	Scaffold(
		topBar = {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.background(MaterialTheme.colorScheme.primary)
					.padding(15.dp),
			) {
				Icon(
					Icons.Default.ArrowBack,
					contentDescription = "back key",
					modifier = Modifier.clickable(onClick = navigateBack)
				)

				Text(
					stringResource(id = R.string.settings),
					modifier = Modifier.padding(start = 15.dp)
				)
			}
		},
	) {
		CheckboxWithText(
			stringResource(id = R.string.show_hidden_apps),
			viewModel.uiState.collectAsState().value.showHiddenApps,
			modifier = Modifier.padding(it)
		) {
			viewModel.changeHideAppToggle(it)
		}
	}
}

