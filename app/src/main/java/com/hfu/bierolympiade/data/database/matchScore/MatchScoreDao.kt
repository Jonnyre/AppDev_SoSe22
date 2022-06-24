package com.hfu.bierolympiade.data.database.matchScore

import androidx.room.*

@Dao
interface MatchScoreDao {

    @Insert
    suspend fun insert(matchScoreDb: MatchScoreDb)

    @Update
    suspend fun update(matchScoreDb: MatchScoreDb)

    @Transaction
    @Query("SELECT * FROM matchScore")
    suspend fun getAll(): List<MatchScoreDb>

    @Transaction
    @Query("SELECT * FROM matchScore WHERE matchScoreId = :id")
    suspend fun getById(id: String): MatchScoreDb?
}