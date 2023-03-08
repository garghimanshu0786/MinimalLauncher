package com.example.ciscolauncher.view.states

import com.example.ciscolauncher.models.AppInfo

data class LauncherViewModelState(
	val allApps: List<AppInfo> = emptyList(),
	val searchQuery: String = "",
	val recentAppsList: List<String> = emptyList(),
	val showHiddenApps: Boolean = false
) {
	val recentApps
		get() = allApps.filter { recentAppsList.contains(it.name) }

	val filteredApps
		get() = if (searchQuery.isEmpty()) {
			allApps.filter { showHiddenApps || it.isHidden.not() }
		} else {
			val resultList = mutableListOf<AppInfo>()
			for (app in allApps.filter { showHiddenApps || it.isHidden.not() }) {
				if (app.name.lowercase().contains(searchQuery.lowercase())) {
					resultList.add(app)
				}
			}
			resultList
		}
}
