package com.hfu.bierolympiade.data.database.player_event_crossref

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PlayerEventCrossRefDao {
    @Insert
    suspend fun insert(event: PlayerEventCrossRefDb)
}