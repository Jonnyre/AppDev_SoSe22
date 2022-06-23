package com.hfu.bierolympiade.data.database.event

import androidx.room.*


@Dao
interface EventDao {

    @Transaction
    suspend fun upsert(event: EventDb) {
        val rowId = insert(event)
        if (rowId == -1L) {
            update(event)
        }
    }

    @Insert
    suspend fun insert(event: EventDb): Long

    @Update
    suspend fun update(event: EventDb)

    @Query("DELETE FROM event WHERE eventId = :id")
    suspend fun deleteById(id: String)

    @Transaction
    @Query("SELECT * FROM event WHERE eventId = :id")
    suspend fun getById(id: String): EventWithMatchesAndGamesAndPlayers?

    @Transaction
    @Query("SELECT * FROM event")
    suspend fun getAll(): List<EventWithMatchesAndGamesAndPlayers>
}