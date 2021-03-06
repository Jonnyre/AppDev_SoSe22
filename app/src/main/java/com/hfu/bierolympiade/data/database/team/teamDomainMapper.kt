package com.hfu.bierolympiade.data.database.team

import com.hfu.bierolympiade.domain.model.Team
import com.hfu.bierolympiade.domain.model.TeamId

fun teamToDb(team: Team): TeamDb = TeamDb(
    teamId = team.id.value,
    created = team.created,
    updated = team.updated,
    deleted = team.deleted,
)

fun teamFromDb(team: TeamWithMatchParticipantAndMatchScore): Team? {
    return Team.create(
        id = TeamId(team.team.teamDb.teamId),
        matchScores = team.matchScores.mapNotNull { it.matchScoreId }
    )
}