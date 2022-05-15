package com.hfu.bierolympiade.data.database

import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId

fun gameToDb(game: Game): GameDb = GameDb(
    id = game.id.value,
    name = game.name,
    status = game.status,
    created = game.created,
    updated = game.updated,
    deleted = game.deleted,
)

fun gameFromDb(game: GameDb): Game? {
    return Game.create(
        id = GameId(game.id),
        name = game.name,
        status = game.status,
    )
}