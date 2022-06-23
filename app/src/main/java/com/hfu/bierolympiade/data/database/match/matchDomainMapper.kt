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

fun matchFromDb(match: MatchWithPlayerAndMatchScore): Match? {
    return Match.create(
        id = MatchId(match.match.matchDb.matchId),
        eventId = EventId(match.match.matchDb.eventId),
        gameId = GameId(match.match.matchDb.gameId),
        date = match.match.matchDb.date,
        type = match.match.matchDb.type,
        state = match.match.matchDb.state,
        matchParticipant = match.match.matchParticipants.mapNotNull { it.matchParticipantId },
        matchScores = match.matchScores.mapNotNull { it.matchScoreId },
        created = match.match.matchDb.created,
        updated = match.match.matchDb.updated,
        deleted = match.match.matchDb.deleted,
    )
}