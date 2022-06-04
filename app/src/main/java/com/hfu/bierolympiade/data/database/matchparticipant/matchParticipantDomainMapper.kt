package com.hfu.bierolympiade.data.database.matchparticipant


import com.hfu.bierolympiade.domain.model.*

fun matchParticipantToDb(matchParticipant: MatchParticipant): MatchParticipantDb = MatchParticipantDb(
    matchParticipantId = matchParticipant.id.value,
    matchId = matchParticipant.matchId.value,
    playerId = matchParticipant.playerId.value,
    teamId = matchParticipant.teamId.value,
    created = matchParticipant.created,
    updated = matchParticipant.updated,
    deleted = matchParticipant.deleted,
)

fun matchParticipantFromDb(matchParticipant: MatchParticipantDb): MatchParticipant? {
    return MatchParticipant.create(
        id = MatchParticipantId(matchParticipant.matchParticipantId),
        matchId = MatchId(matchParticipant.matchId),
        playerId = PlayerId(matchParticipant.playerId),
        teamId = TeamId(matchParticipant.teamId),
    )
}