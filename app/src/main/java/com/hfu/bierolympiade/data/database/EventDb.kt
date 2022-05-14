package com.hfu.bierolympiade.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime


@Entity(tableName = "event")
data class EventDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val location: String,
    val participants: Int,
    val date: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)