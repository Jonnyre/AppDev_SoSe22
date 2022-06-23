package com.hfu.bierolympiade.data.database.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TeamDao {
    @Insert
    suspend fun insert(team: TeamDb)

    @Transaction
    @Query("SELECT * FROM team")
    suspend fun getAll(): List<TeamWithMatchParticipantAndMatchScore>

    @Transaction
    @Query("SELECT * FROM team WHERE teamId = :id")
    suspend fun getById(id: String): TeamWithMatchParticipantAndMatchScore?
}