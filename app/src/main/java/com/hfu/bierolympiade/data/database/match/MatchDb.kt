package com.hfu.bierolympiade.data.database.match

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.hfu.bierolympiade.data.database.matchScore.MatchScoreDb
import com.hfu.bierolympiade.data.database.matchparticipant.MatchParticipantDb
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

data class MatchWithPlayer(
    @Embedded val matchDb: MatchDb,
    @Relation(
        parentColumn = "matchId",
        entityColumn = "matchId"
    )
    val matchParticipants: List<MatchParticipantDb>
)

data class MatchWithPlayerAndMatchScore(
    @Embedded val match: MatchWithPlayer,
    @Relation(
        parentColumn = "matchId",
        entityColumn = "matchId",
    )
    val matchScores: List<MatchScoreDb>
)