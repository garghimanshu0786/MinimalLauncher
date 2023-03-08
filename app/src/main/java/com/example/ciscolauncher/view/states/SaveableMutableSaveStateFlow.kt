package com.example.ciscolauncher.view.states

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SaveableMutableSaveStateFlow<T>(
	private val savedStateHandle: SavedStateHandle,
	private val key: String,
	defaultValue: T,
) {
	private val state: MutableStateFlow<T> =
		MutableStateFlow(savedStateHandle.get<T>(key) ?: defaultValue)

	val value: T
		get() = state.value

	fun asStateFlow(): StateFlow<T> = state

	fun update(function: (T) -> T) {
		state.update {
			savedStateHandle[key] = it
			function(it)
		}
	}
}