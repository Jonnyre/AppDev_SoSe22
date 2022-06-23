package com.hfu.bierolympiade.data.database.matchScore

import com.hfu.bierolympiade.domain.model.*

fun matchScoreToDb(matchScore: MatchScore): MatchScoreDb = MatchScoreDb(
    matchScoreId = matchScore.id.value,
    matchId = matchScore.matchId.value,
    playerId = matchScore.playerId.value,
    teamId = matchScore.teamId.value,
    value = matchScore.value,
    created = matchScore.created,
    updated = matchScore.updated,
    deleted = matchScore.deleted,
)

fun matchScoreFromDb(matchScore: MatchScoreDb): MatchScore? {
    return MatchScore.create(
        id = MatchScoreId(matchScore.matchScoreId),
        matchId = MatchId(matchScore.matchId),
        playerId = PlayerId(matchScore.playerId),
        teamId = TeamId(matchScore.teamId),
        value = matchScore.value,
        created = matchScore.created,
        updated = matchScore.updated,
        deleted = matchScore.deleted,
    )
}