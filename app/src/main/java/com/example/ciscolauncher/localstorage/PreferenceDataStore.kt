package com.example.ciscolauncher.localstorage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val PREFERENCES_NAME = "app_preferences"

private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class PreferenceDataStore @Inject constructor(
	private val context: Context
) : ILocalStorage {

	override suspend fun putBoolean(key: String, value: Boolean) {
		val preferencesKey = booleanPreferencesKey(key)
		context.dataStore.edit { preferences ->
			preferences[preferencesKey] = value
		}
	}

	override suspend fun getBoolean(key: String): Boolean {
		val preferencesKey = booleanPreferencesKey(key)
		val preferences = context.dataStore.data.first()
		return preferences[preferencesKey] ?: false
	}
}