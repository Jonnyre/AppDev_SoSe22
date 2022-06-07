package com.hfu.bierolympiade.domain.model

import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb

class PlayerEventCrossRef private constructor(
    val playerId: PlayerId,
    val eventId: EventId
) {
    companion object {
        fun create(
            playerId: PlayerId,
            eventId: EventId
        ): PlayerEventCrossRef {
            return PlayerEventCrossRef(playerId, eventId)
        }
    }
}