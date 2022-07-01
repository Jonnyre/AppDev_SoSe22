package com.hfu.bierolympiade.data.database.game

import androidx.room.*
import com.hfu.bierolympiade.data.database.player.PlayerDb
import com.hfu.bierolympiade.domain.model.Game

@Dao
interface GameDao {
    @Insert
    suspend fun insert(game: GameDb)

    @Transaction
    @Query("SELECT * FROM game")
    suspend fun getAll(): List<GameWithMatchesAndType>

    @Update
    suspend fun update(game: GameDb)

    @Transaction
    @Query("SELECT * FROM game WHERE gameId = :id")
    suspend fun getById(id: String): GameWithMatchesAndType?


    @Query("DELETE FROM game WHERE gameId = :gameId")
    suspend fun deleteByGameId(gameId: String)
}