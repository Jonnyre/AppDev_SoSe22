package com.hfu.bierolympiade.data.database.gameType

import androidx.room.*
import com.hfu.bierolympiade.data.database.player.PlayerDb

@Dao
interface GameTypeDao {
    @Insert
    suspend fun insert(gameType: GameTypeDb)
    @Transaction
    @Query("SELECT * FROM gameType")
    suspend fun getAll(): List<GameTypeDb>

    @Transaction
    @Query("SELECT * FROM gameType WHERE gameTypeId = :id")
    suspend fun getById(id: String): GameTypeDb?
}