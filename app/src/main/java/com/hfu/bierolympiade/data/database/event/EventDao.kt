package com.hfu.bierolympiade.data.database.event

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface EventDao {

    @Insert
    suspend fun insert(event: EventDb)

    @Transaction
    @Query("SELECT * FROM event WHERE eventId = :id")
    suspend fun getById(id: String): EventWithMatchesAndGamesAndPlayers?

    @Transaction
    @Query("SELECT * FROM event")
    suspend fun getAll(): List<EventWithMatchesAndGamesAndPlayers>
}