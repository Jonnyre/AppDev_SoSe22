package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.TeamRepository
import com.hfu.bierolympiade.domain.model.Team
import com.hfu.bierolympiade.domain.model.TeamId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDemoTeamsUseCase @Inject constructor(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (teamRepository.getAllTeames().isNotEmpty()) return@withContext

        listOfNotNull(
            Team.create(
                id = TeamId("6e3cd742-61cc-4cf4-a203-b7d9a65b8618"),
                matchScores = emptyList()
            ),
            Team.create(
                id = TeamId("8c3568e1-a466-41f6-9255-0ff62350adbd"),
                matchScores = emptyList()
            ),
        ).forEach {
            teamRepository.addTeam(it)
        }
    }
}