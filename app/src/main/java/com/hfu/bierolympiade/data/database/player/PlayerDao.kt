package com.hfu.bierolympiade.data.database.player

import androidx.room.*

@Dao
interface PlayerDao {
    @Insert
    suspend fun insert(player: PlayerDb)

    @Update
    suspend fun update(player: PlayerDb)

    @Transaction
    @Query("SELECT * FROM player")
    suspend fun getAll(): List<PlayerWithEventAndMatchScoreAndMatches>

    @Transaction
    @Query("SELECT * FROM player WHERE playerId = :id")
    suspend fun getById(id: String): PlayerWithEventAndMatchScoreAndMatches?
}