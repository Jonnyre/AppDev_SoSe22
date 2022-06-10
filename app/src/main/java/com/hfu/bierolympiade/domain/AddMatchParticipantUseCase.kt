package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchParticipantRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddMatchParticipantUseCase @Inject constructor(
    private val matchParticipantRepository: MatchParticipantRepository
) {
    suspend operator fun invoke(matchId: MatchId, playerId: PlayerId, teamId: TeamId): String? = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newMatchParticipant = MatchParticipant.create(
            MatchParticipantId(uniqueID),
            matchId = matchId,
            playerId = playerId,
            teamId = teamId
        )
        if(newMatchParticipant != null)  {
            matchParticipantRepository.addMatchParticipant(newMatchParticipant)
            return@withContext uniqueID
        }
        return@withContext null
    }
}