package com.hfu.bierolympiade.data.database.player_event_crossref

import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.PlayerEventCrossRef
import com.hfu.bierolympiade.domain.model.PlayerId


fun playerEventCrossRefToDb(playerEventCrossRef: PlayerEventCrossRef): PlayerEventCrossRefDb = PlayerEventCrossRefDb(
    playerId = playerEventCrossRef.playerId.value,
    eventId = playerEventCrossRef.eventId.value
)

fun playerEventCrossRefFromDb(playerEventCrossRefDb: PlayerEventCrossRefDb): PlayerEventCrossRef {
    return PlayerEventCrossRef.create(
        playerId = PlayerId(playerEventCrossRefDb.playerId),
        eventId = EventId(playerEventCrossRefDb.eventId)
    )
}
