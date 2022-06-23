package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.TeamRepository
import com.hfu.bierolympiade.domain.model.Team
import com.hfu.bierolympiade.domain.model.TeamId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddTeamUseCase @Inject constructor(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(): String? = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newTeam = Team.create(
            TeamId(uniqueID),
            matchScores = emptyList()
        )
        if(newTeam != null)  {
            teamRepository.addTeam(newTeam)
            return@withContext uniqueID
        }
        return@withContext null
    }
}