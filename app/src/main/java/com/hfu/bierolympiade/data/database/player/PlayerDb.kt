package com.hfu.bierolympiade.data.database.player

import androidx.room.*
import com.hfu.bierolympiade.data.database.event.EventDb
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb
import java.time.ZonedDateTime


@Entity(tableName = "player")
data class PlayerDb(
    @PrimaryKey
    val playerId: String,
    val name: String,
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