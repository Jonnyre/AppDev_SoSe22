package com.hfu.bierolympiade.data.database.player

import com.hfu.bierolympiade.data.database.event.eventFromDb
import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId

fun playerToDb(player: Player): PlayerDb = PlayerDb(
    id = player.id.value,
    name = player.name,
    description = player.description,
    created = player.created,
    updated = player.updated,
    deleted = player.deleted,
)

fun playerFromDb(playerWithEvent: PlayerWithEvent): Player? {
    return Player.create(
        id = PlayerId(playerWithEvent.player.id),
        name = playerWithEvent.player.name,
        description = playerWithEvent.player.description,
        events = playerWithEvent.events.mapNotNull { eventFromDb(it) },
    )
}