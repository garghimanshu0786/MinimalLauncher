package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ciscolauncher.R
import com.example.ciscolauncher.models.AppInfo
import com.example.ciscolauncher.viewmodels.LauncherViewModel

@Composable
fun RecentAppsView(
	recentApps: List<AppInfo>,
	viewModel: LauncherViewModel,
	expandedApp: MutableState<String>
) {
	if (recentApps.isNotEmpty()) {
		Text(
			stringResource(R.string.recent_apps),
			textAlign = TextAlign.Center,
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 15.dp)
		)
	}

	LazyVerticalGrid(
		GridCells.Fixed(4),
		verticalArrangement = Arrangement.spacedBy(20.dp),
	) {
		items(recentApps) {
			AppView(it.icon, it.name, it.packageName, viewModel, expandedApp)
		}
	}
}