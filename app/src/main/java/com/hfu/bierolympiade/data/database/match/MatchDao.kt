package com.hfu.bierolympiade.data.database.match

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface MatchDao {

    @Insert
    suspend fun insert(match: MatchDb)

    @Transaction
    @Query("SELECT * FROM matchTable")
    suspend fun getAll(): List<MatchWithPlayer>

    @Transaction
    @Query("SELECT * FROM matchTable WHERE matchId = :id")
    suspend fun getById(id: String): MatchWithPlayer?
}