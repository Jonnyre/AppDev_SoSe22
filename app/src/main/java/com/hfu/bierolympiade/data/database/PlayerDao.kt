package com.hfu.bierolympiade.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PlayerDao {

    @Insert
    suspend fun insert(player: PlayerDb)

    @Query("SELECT * FROM player")
    suspend fun getAll(): List<PlayerDb>

    @Query("SELECT * FROM player WHERE id = :id")
    suspend fun getById(id: String): PlayerDb?
}