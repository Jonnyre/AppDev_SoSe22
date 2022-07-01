package com.hfu.bierolympiade.data.database.player

import androidx.room.*
import com.hfu.bierolympiade.data.database.event.EventDb
import com.hfu.bierolympiade.data.database.matchScore.MatchScoreDb
import com.hfu.bierolympiade.data.database.matchparticipant.MatchParticipantDb
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb
import java.time.ZonedDateTime


@Entity(tableName = "player")
data class PlayerDb(
    @PrimaryKey
    val playerId: String,
    val name: String,
    val music: String,
    val description: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class PlayerWithEvent(
    @Embedded val player: PlayerDb,
    @Relation(
        parentColumn = "playerId",
        entityColumn = "eventId",
        associateBy = Junction(PlayerEventCrossRefDb::class)
    )
    val events: List<EventDb>
)

data class PlayerWithEventAndMatchScore(
    @Embedded val player: PlayerWithEvent,
    @Relation(
        parentColumn = "playerId",
        entityColumn = "playerId",
    )
    val matchScores: List<MatchScoreDb>
)

data class PlayerWithEventAndMatchScoreAndMatches(
    @Embedded val player: PlayerWithEventAndMatchScore,
    @Relation(
        parentColumn = "playerId",
        entityColumn = "playerId",
    )
    val matchParticipants: List<MatchParticipantDb>
)