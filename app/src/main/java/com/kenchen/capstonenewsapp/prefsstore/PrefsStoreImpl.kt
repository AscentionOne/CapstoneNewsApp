package com.kenchen.capstonenewsapp.prefsstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class PrefsStoreImpl(val context: Context) : PrefsStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun isDataUsage(): Flow<Boolean> = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.DATA_MODE_KEY] ?: false }

    override suspend fun toggleDataUsage() {
        context.dataStore.data.first()
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.DATA_MODE_KEY] = !(settings[PreferencesKeys.DATA_MODE_KEY]
                ?: false)
        }
    }

    private object PreferencesKeys {
        val DATA_MODE_KEY = booleanPreferencesKey("data_usage_enabled")
    }
}
