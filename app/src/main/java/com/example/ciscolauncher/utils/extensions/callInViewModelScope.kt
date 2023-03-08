package com.example.ciscolauncher.utils.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <R> ViewModel.callInViewModelScope(block: suspend () -> R) {
	viewModelScope.launch {
		block()
	}
}

fun <T> List<T>.mapIf(shouldChange: (T) -> Boolean, change: (T) -> T) = map {
	if (shouldChange(it)) {
		change(it)
	} else {
		it
	}
}