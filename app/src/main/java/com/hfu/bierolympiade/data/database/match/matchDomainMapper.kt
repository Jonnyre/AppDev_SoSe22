package com.hfu.bierolympiade.data.database.match


import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.Match
import com.hfu.bierolympiade.domain.model.MatchId

fun matchToDb(match: Match): MatchDb = MatchDb(
    matchId = match.id.value,
    eventId = match.eventId.value,
    gameId = match.gameId.value,
    date = match.date,
    type = match.type,
    state = match.state,
    created = match.created,
    updated = match.updated,
    deleted = match.deleted,
)

fun matchFromDb(match: MatchWithPlayer): Match? {
    return Match.create(
        id = MatchId(match.matchDb.matchId),
        eventId = EventId(match.matchDb.eventId),
        gameId = GameId(match.matchDb.gameId),
        date = match.matchDb.date,
        type = match.matchDb.type,
        state = match.matchDb.state,
        matchParticipant = match.matchParticipants.mapNotNull { it.matchParticipantId },
        created = match.matchDb.created,
        updated = match.matchDb.updated,
        deleted = match.matchDb.deleted,
    )
}