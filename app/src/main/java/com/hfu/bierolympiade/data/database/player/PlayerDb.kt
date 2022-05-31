package com.hfu.bierolympiade.data.database.player

import androidx.room.*
import com.hfu.bierolympiade.data.database.event.EventWithEverything
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb
import java.time.ZonedDateTime


@Entity(tableName = "player")
data class PlayerDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class PlayerWithEvent(
    @Embedded val player: PlayerDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "playerId",
        associateBy = Junction(PlayerEventCrossRefDb::class)
    )
    val events: List<EventWithEverything>
)