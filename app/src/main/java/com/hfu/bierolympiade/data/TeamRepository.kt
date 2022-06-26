package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.team.TeamDao
import com.hfu.bierolympiade.data.database.team.teamFromDb
import com.hfu.bierolympiade.data.database.team.teamToDb
import com.hfu.bierolympiade.domain.model.Team
import com.hfu.bierolympiade.domain.model.TeamId
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val dao: TeamDao
) {

    suspend fun getAllTeames(): List<Team> = dao.getAll().mapNotNull { teamFromDb(it) }

    suspend fun getTeamById(id: TeamId): Team? = dao.getById(id.value)?.let { teamFromDb(it) }

    suspend fun addTeam(Team: Team) {
        dao.insert(teamToDb(Team))
    }
}