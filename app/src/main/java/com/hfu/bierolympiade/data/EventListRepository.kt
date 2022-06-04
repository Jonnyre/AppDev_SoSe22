package com.hfu.bierolympiade.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.json.JSONArray

/** Provides CRUD operations for user settings. */
class EventListRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun updatePlayerList(addedPlayers: List<String>) {
        dataStore.edit {

        }
    }

    private companion object {
        private val KEY_PLAYERS = stringPreferencesKey("addedPlayers")
    }
}



