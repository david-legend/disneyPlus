package com.davidcobbina.disneyplus.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val USER_EMAIL_KEY = stringPreferencesKey("user_email")

class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val getEmail: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }
}