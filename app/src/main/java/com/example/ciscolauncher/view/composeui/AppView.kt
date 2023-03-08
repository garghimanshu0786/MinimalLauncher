package com.example.ciscolauncher.view.composeui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ciscolauncher.viewmodels.LauncherViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppView(
	icon: Drawable,
	name: String,
	packageName: String,
	viewModel: LauncherViewModel,
	expandedApp: MutableState<String>
) {
	val context = LocalContext.current
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.combinedClickable(onClick = {
			viewModel.addRecentApp(name)
			context.packageManager
				.getLaunchIntentForPackage(packageName)
				.let { context.startActivity(it) }
		}, onLongClick = {
			expandedApp.value = name
		})
	) {
		AsyncImage(
			model = icon,
			contentDescription = "icon",
			modifier = Modifier.size(60.dp)
		)

		Text(
			name,
			modifier = Modifier.padding(top = 5.dp),
			maxLines = 2,
			overflow = TextOverflow.Ellipsis,
			textAlign = TextAlign.Center
		)
	}
}