package com.hfu.bierolympiade.data.database.player_event_crossref

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerEventCrossRefDao {
    @Insert
    suspend fun insert(event: PlayerEventCrossRefDb)

    @Query("DELETE FROM PlayerEventCrossRefDb WHERE eventId = :id")
    suspend fun deleteAllFromEventId(id: String)
}