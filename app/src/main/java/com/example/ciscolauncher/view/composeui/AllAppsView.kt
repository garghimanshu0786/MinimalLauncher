package com.example.ciscolauncher.view.composeui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.ciscolauncher.R
import com.example.ciscolauncher.models.AppInfo
import com.example.ciscolauncher.viewmodels.LauncherViewModel

@Composable
fun AllAppsView(
	filteredApps: List<AppInfo>, viewModel: LauncherViewModel, expandedApp: MutableState<String>
) {
	val context = LocalContext.current
	if (filteredApps.isNotEmpty()) {
		Text(
			stringResource(R.string.all_apps),
			textAlign = TextAlign.Center,
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 15.dp, top = 10.dp)
		)
	}
	LazyVerticalGrid(
		GridCells.Fixed(4),
		verticalArrangement = Arrangement.spacedBy(20.dp),
	) {
		items(filteredApps) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 10.dp, top = 5.dp)
			) {
				AppView(it.icon, it.name, it.packageName, viewModel, expandedApp)

				DropdownMenu(offset = DpOffset(x = (-66).dp, y = (-10).dp),
					modifier = Modifier.width(100.dp),
					expanded = expandedApp.value == it.name,
					onDismissRequest = {
						expandedApp.value = ""
					}) {

					DropdownMenuItem(onClick = {
						expandedApp.value = ""
						context.startActivity(
							Intent(
								Intent.ACTION_UNINSTALL_PACKAGE,
								Uri.parse("package:${it.packageName}")
							)
						)
					}, text = { Text(stringResource(id = R.string.uninstall)) })

					DropdownMenuItem(onClick = { viewModel.toggleHideAppFlag(it.name) },
						text = { Text(stringResource(if (it.isHidden) R.string.unhide_app else R.string.hide_app)) })
				}
			}
		}
	}
}