package com.hfu.bierolympiade.data.database.game

import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.GameTypeId

fun gameToDb(game: Game): GameDb = GameDb(
    gameId = game.id.value,
    gameTypeId = game.gameTypeId.value,
    eventId = game.eventId.value,
    status = game.status,
    teamSize = game.teamSize,
    rules = game.rules,
    winCondition = game.winCondition,
    points = game.points,
    created = game.created,
    updated = game.updated,
    deleted = game.deleted,
)

fun gameFromDb(gameWithMatches: GameWithMatchesAndType): Game? {
    return Game.create(
        id = GameId(gameWithMatches.game.game.gameId),
        gameTypeId = GameTypeId(gameWithMatches.game.game.gameTypeId),
        name = gameWithMatches.gameType.name,
        icon = gameWithMatches.gameType.icon,
        rules = gameWithMatches.game.game.rules,
        status = gameWithMatches.game.game.status,
        matches = gameWithMatches.game.matches.mapNotNull { it.matchId },
        eventId = EventId(gameWithMatches.game.game.eventId),
        teamSize = gameWithMatches.game.game.teamSize,
        winCondition = gameWithMatches.game.game.winCondition,
        points = gameWithMatches.game.game.points,
        created = gameWithMatches.game.game.created,
        updated = gameWithMatches.game.game.updated,
        deleted = gameWithMatches.game.game.deleted,
    )
}