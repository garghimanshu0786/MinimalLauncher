package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.ciscolauncher.R
import com.example.ciscolauncher.viewmodels.LauncherViewModel

@Composable
fun SearchBar(searchQuery: String, viewModel: LauncherViewModel) {
	val focusManager = LocalFocusManager.current
	TextField(
		modifier = Modifier.fillMaxWidth(),
		value = searchQuery,
		onValueChange = { viewModel.searchApps(it) },
		placeholder = { Text(text = stringResource(R.string.search_all_apps)) },
		leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
		trailingIcon = {
			if (searchQuery.isNotBlank())
				Icon(Icons.Default.Clear, contentDescription = "Clear search", Modifier.clickable {
					viewModel.searchApps("")
					focusManager.clearFocus()
				})
		},
		keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
		keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
	)
}

