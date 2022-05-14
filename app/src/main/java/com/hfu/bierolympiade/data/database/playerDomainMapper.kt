package com.hfu.bierolympiade.data.database

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

fun playerFromDb(player: PlayerDb): Player? {
    return Player.create(
        id = PlayerId(player.id),
        name = player.name,
        description = player.description,
    )
}