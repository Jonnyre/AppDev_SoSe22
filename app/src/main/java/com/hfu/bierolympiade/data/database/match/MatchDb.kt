package com.hfu.bierolympiade.data.database.match

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime


@Entity(tableName = "matchTable")
data class MatchDb(
    @PrimaryKey
    val matchId: String,
    val eventId: String,
    val gameId: String,
    val date: ZonedDateTime,
    val type: Int,
    val state: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)