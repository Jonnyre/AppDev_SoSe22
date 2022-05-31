package com.hfu.bierolympiade.data.database.player_event_crossref

import androidx.room.Entity

@Entity(primaryKeys = ["eventId", "playerId"])
data class PlayerEventCrossRefDb(
        val eventId: String,
        val playerId: String
)