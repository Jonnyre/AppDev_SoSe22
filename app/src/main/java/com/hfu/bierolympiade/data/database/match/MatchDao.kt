package com.hfu.bierolympiade.data.database.match

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MatchDao {

    @Insert
    suspend fun insert(match: MatchDb)

    @Query("SELECT * FROM matchTable")
    suspend fun getAll(): List<MatchDb>

    @Query("SELECT * FROM matchTable WHERE id = :id")
    suspend fun getById(id: String): MatchDb?
}