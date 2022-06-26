package com.hfu.bierolympiade.data.database.gameType

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

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