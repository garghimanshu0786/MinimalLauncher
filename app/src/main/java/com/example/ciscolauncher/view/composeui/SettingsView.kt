package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsView(openSettings: () -> Unit) {
	Row(modifier = Modifier.fillMaxWidth()) {
		Icon(
			Icons.Default.Settings,
			contentDescription = "Settings",
			modifier = Modifier.clickable(onClick = openSettings)
		)

		Spacer(modifier = Modifier.weight(1f))
	}
}