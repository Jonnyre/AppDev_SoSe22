package com.hfu.bierolympiade.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface EventDao {

    @Insert
    suspend fun insert(event: EventDb)

    @Query("SELECT * FROM event")
    suspend fun getAll(): List<EventDb>

    @Query("SELECT * FROM event WHERE id = :id")
    suspend fun getById(id: String): EventDb?
}