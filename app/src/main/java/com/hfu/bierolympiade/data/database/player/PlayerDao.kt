package com.hfu.bierolympiade.data.database.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDao {
    @Insert
    suspend fun insert(player: PlayerDb)

    @Query("SELECT * FROM player")
    suspend fun getAll(): List<PlayerWithEvent>

    @Query("SELECT * FROM player WHERE playerId = :id")
    suspend fun getById(id: String): PlayerWithEvent?
}