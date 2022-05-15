package com.hfu.bierolympiade.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {
    @Insert
    suspend fun insert(game: GameDb)

    @Query("SELECT * FROM game")
    suspend fun getAll(): List<GameDb>

    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getById(id: String): GameDb?
}