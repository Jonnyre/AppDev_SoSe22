package com.hfu.bierolympiade.data.database.game

import com.hfu.bierolympiade.data.database.match.matchFromDb
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId

fun gameToDb(game: Game): GameDb = GameDb(
    gameId = game.id.value,
    name = game.name,
    eventId = game.eventId.value,
    status = game.status,
    created = game.created,
    updated = game.updated,
    deleted = game.deleted,
)

fun gameFromDb(gameWithMatches: GameWithMatches): Game? {
    return Game.create(
        id = GameId(gameWithMatches.game.gameId),
        name = gameWithMatches.game.name,
        status = gameWithMatches.game.status,
        matches = gameWithMatches.matches.mapNotNull { it.matchId },
        eventId = EventId(gameWithMatches.game.eventId),
        created = gameWithMatches.game.created,
        updated = gameWithMatches.game.updated,
        deleted = gameWithMatches.game.deleted,
    )
}