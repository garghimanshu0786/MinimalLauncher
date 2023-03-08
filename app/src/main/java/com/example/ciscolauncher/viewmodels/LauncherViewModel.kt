package com.example.ciscolauncher.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciscolauncher.localstorage.ILocalStorage
import com.example.ciscolauncher.utils.AppUtility
import com.example.ciscolauncher.utils.extensions.callInViewModelScope
import com.example.ciscolauncher.utils.extensions.mapIf
import com.example.ciscolauncher.view.states.LauncherViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
	private val localStorage: ILocalStorage,
	private val appUtility: AppUtility,
	private val savedStateHandle: SavedStateHandle
) : ViewModel() {
	private val viewModelState: MutableStateFlow<LauncherViewModelState> =
		MutableStateFlow(
			LauncherViewModelState(
				searchQuery = savedStateHandle.get<String>(SEARCH_QUERY_SAVED_STATE_HANDLE_KEY)
					.orEmpty(),
				recentAppsList = savedStateHandle.get<List<String>>(
					RECENT_APPS_SAVED_STATE_HANDLE_KEY
				)
					.orEmpty()
			)
		)

	val uiState = viewModelState.stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(),
		initialValue = LauncherViewModelState()
	)

	init {
		callInViewModelScope {
			viewModelState.update {
				it.copy(
					allApps = appUtility.getInstalledApps(),
					showHiddenApps = getHideAppToggleFromLocalStorage()
				)
			}
		}
	}

	fun searchApps(searchQuery: String) = callInViewModelScope {
		savedStateHandle[SEARCH_QUERY_SAVED_STATE_HANDLE_KEY] = searchQuery
		viewModelState.update { it.copy(searchQuery = searchQuery) }
	}

	fun addRecentApp(name: String) = callInViewModelScope {
		val recentApps =
			savedStateHandle.get<List<String>>(RECENT_APPS_SAVED_STATE_HANDLE_KEY).orEmpty().take(3)
				.toMutableList()
		if (recentApps.contains(name).not()) {
			recentApps.add(name)
			viewModelState.update { it.copy(recentAppsList = recentApps) }
			savedStateHandle[RECENT_APPS_SAVED_STATE_HANDLE_KEY] = recentApps
		}
	}

	fun toggleHideAppFlag(name: String) = callInViewModelScope {
		val updatedAppsList = viewModelState.value.allApps.mapIf({ it.name == name }) {
			it.copy(isHidden = it.isHidden.not())
		}
		viewModelState.update { it.copy(allApps = updatedAppsList) }
	}

	private fun getHideAppToggleFromLocalStorage() = runBlocking {
		localStorage.getBoolean(HIDE_APPS_LOCAL_STORAGE_KEY)
	}

	fun changeHideAppToggle(hideApps: Boolean) = callInViewModelScope {
		viewModelState.update { it.copy(showHiddenApps = hideApps) }
		localStorage.putBoolean(HIDE_APPS_LOCAL_STORAGE_KEY, hideApps)
	}

	companion object {
		const val SEARCH_QUERY_SAVED_STATE_HANDLE_KEY = "search_query"
		const val RECENT_APPS_SAVED_STATE_HANDLE_KEY = "recent_apps"
		const val HIDE_APPS_LOCAL_STORAGE_KEY = "hidden_apps"
	}
}