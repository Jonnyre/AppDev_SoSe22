package com.hfu.bierolympiade.data.database.matchScore

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MatchScoreDao {

    @Insert
    suspend fun insert(matchScoreDb: MatchScoreDb)

    @Transaction
    @Query("SELECT * FROM matchScore")
    suspend fun getAll(): List<MatchScoreDb>

    @Transaction
    @Query("SELECT * FROM matchScore WHERE matchScoreId = :id")
    suspend fun getById(id: String): MatchScoreDb?
}