package com.hfu.bierolympiade.data.database.matchScore

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "matchScore")
data class MatchScoreDb(
    @PrimaryKey
    val matchScoreId: String,
    val matchId: String,
    val teamId: String,
    val playerId: String,
    val value: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)