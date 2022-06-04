package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchParticipantRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPlayerFromMatchParticipantUseCase  @Inject constructor(
    private val getPlayerByIdUseCase: GetPlayerByIdUseCase,
    private val matchParticipantRepository: MatchParticipantRepository
){
    suspend operator fun invoke(matchParticipantId: MatchParticipantId) = withContext(Dispatchers.Default) {
        val matchParticipant: MatchParticipant =
            matchParticipantRepository.getMatchParticipantById(matchParticipantId)
                ?: return@withContext null
        return@withContext PlayerWithTeam(
            matchParticipant.teamId,
            getPlayerByIdUseCase(matchParticipant.playerId)
        )
    }
}

data class PlayerWithTeam(
    val teamId: TeamId,
    val player: Player?
)