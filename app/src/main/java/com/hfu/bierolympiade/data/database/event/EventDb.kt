package com.hfu.bierolympiade.data.database.event

import androidx.room.*
import com.hfu.bierolympiade.data.database.game.GameWithMatches
import com.hfu.bierolympiade.data.database.match.MatchDb
import com.hfu.bierolympiade.data.database.player.PlayerWithEvent
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb
import java.time.ZonedDateTime


@Entity(tableName = "event")
data class EventDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val location: String,
    val date: String,
    val fees: Int?,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class EventWithEverything(
    @Embedded val event: EventDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "eventId",
    )
    val matches: List<MatchDb>,
    @Relation(
        parentColumn = "id",
        entityColumn = "eventId",
    )
    val games: List<GameWithMatches>,

    @Relation(
        parentColumn = "id",
        entityColumn = "eventId",
        associateBy = Junction(PlayerEventCrossRefDb::class)
    )
    val players: List<PlayerWithEvent>
)