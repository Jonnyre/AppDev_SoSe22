package com.hfu.bierolympiade.data.database.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface GameDao {
    @Insert
    suspend fun insert(game: GameDb)

    @Transaction
    @Query("SELECT * FROM game")
    suspend fun getAll(): List<GameWithMatches>

    @Transaction
    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getById(id: String): GameWithMatches?
}