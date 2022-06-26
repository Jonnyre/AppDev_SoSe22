package com.hfu.bierolympiade.domain.model

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