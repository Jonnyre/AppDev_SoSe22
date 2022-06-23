package com.hfu.bierolympiade.data.database.player_event_crossref

import androidx.room.Entity
import androidx.room.ForeignKey
import com.hfu.bierolympiade.data.database.event.EventDb
import com.hfu.bierolympiade.data.database.player.PlayerDb

@Entity(
    primaryKeys = ["eventId", "playerId"],
    foreignKeys = [
        ForeignKey(
            entity = EventDb::class,
            parentColumns = ["eventId"],
            childColumns = ["eventId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlayerDb::class,
            parentColumns = ["playerId"],
            childColumns = ["playerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PlayerEventCrossRefDb(
    val eventId: String,
    val playerId: String
)