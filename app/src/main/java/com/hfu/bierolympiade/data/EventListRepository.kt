package com.hfu.bierolympiade.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject

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



