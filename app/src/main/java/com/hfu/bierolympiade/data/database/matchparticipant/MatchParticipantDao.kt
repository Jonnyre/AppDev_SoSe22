package com.hfu.bierolympiade.data.database.matchparticipant

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MatchParticipantDao {

    @Insert
    suspend fun insert(match: MatchParticipantDb)

    @Query("SELECT * FROM matchParticipant")
    suspend fun getAll(): List<MatchParticipantDb>

    @Query("SELECT * FROM matchParticipant WHERE matchParticipantId = :id")
    suspend fun getById(id: String): MatchParticipantDb?

}