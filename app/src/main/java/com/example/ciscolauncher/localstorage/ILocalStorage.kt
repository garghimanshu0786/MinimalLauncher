package com.example.ciscolauncher.localstorage

interface ILocalStorage {
	suspend fun putBoolean(key: String, value: Boolean)
	suspend fun getBoolean(key: String): Boolean
}