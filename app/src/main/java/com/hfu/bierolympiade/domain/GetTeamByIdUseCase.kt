package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.TeamRepository
import com.hfu.bierolympiade.domain.model.TeamId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTeamByIdUseCase @Inject constructor(
    private val teamRepository: TeamRepository
){
    suspend operator fun invoke(teamId: TeamId) = withContext(Dispatchers.Default) {
        teamRepository.getTeamById(teamId)
    }
}