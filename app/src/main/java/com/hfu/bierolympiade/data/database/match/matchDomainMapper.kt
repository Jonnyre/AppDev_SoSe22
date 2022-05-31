package com.hfu.bierolympiade.data.database.match


import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.Match
import com.hfu.bierolympiade.domain.model.MatchId

fun matchToDb(match: Match): MatchDb = MatchDb(
    id = match.id.value,
    eventId = match.eventId.value,
    gameId = match.gameId.value,
    date = match.date,
    type = match.type,
    state = match.state,
    created = match.created,
    updated = match.updated,
    deleted = match.deleted,
)

fun matchFromDb(match: MatchDb): Match? {
    return Match.create(
        id = MatchId(match.id),
        eventId = EventId(match.eventId),
        gameId = GameId(match.gameId),
        date = match.date,
        type = match.type,
        state = match.state,
        created = match.created,
        updated = match.updated,
        deleted = match.deleted,
    )
}