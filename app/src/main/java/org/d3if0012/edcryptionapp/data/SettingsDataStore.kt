package org.d3if0012.edcryptionapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*

private const val PREFERENCES_NAME = "preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_NAME
)

class SettingsDataStore(prefDataStore: DataStore<Preferences>) {
    private val IS_LINEAR_LAYOUT = booleanPreferencesKey("is_linear_layout")
    val preferenceFlow: Flow<Boolean> = prefDataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[IS_LINEAR_LAYOUT] ?: true }
    suspend fun saveLayout(isLinearLayout: Boolean, context: Context) {
        context.dataStore.edit { it[IS_LINEAR_LAYOUT] = isLinearLayout }
    }
}