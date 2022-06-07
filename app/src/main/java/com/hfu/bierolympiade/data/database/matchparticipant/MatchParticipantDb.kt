package com.hfu.bierolympiade.data.database.matchparticipant

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "matchParticipant")
data class MatchParticipantDb(
    @PrimaryKey
    val matchParticipantId: String,
    val matchId: String,
    val playerId: String,
    val teamId: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)