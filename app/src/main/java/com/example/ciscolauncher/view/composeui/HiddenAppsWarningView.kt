package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ciscolauncher.R
import com.example.ciscolauncher.models.AppInfo

@Composable
fun HiddenAppsWarningView(
	showHiddenApps: Boolean,
	filteredApps: List<AppInfo>,
	expandedApp: MutableState<String>
) {
	if (showHiddenApps && filteredApps.any { it.isHidden }) {
		Text(
			stringResource(id = R.string.hidden_apps_visible),
			color = MaterialTheme.colorScheme.tertiary,
			modifier = Modifier.padding(top = 10.dp),
			textAlign = TextAlign.Center
		)
		// To hide popup if any open
		expandedApp.value = ""
	}
}