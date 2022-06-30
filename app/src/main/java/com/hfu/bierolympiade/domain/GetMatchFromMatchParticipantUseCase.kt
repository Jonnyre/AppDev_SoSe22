package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchParticipantRepository
import com.hfu.bierolympiade.domain.model.MatchParticipant
import com.hfu.bierolympiade.domain.model.MatchParticipantId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMatchFromMatchParticipantUseCase @Inject constructor(
    private val getMatchById: GetMatchByIdUseCase,
    private val matchParticipantRepository: MatchParticipantRepository
) {
    suspend operator fun invoke(matchParticipantId: MatchParticipantId) =
        withContext(Dispatchers.Default) {
            val matchParticipant: MatchParticipant =
                matchParticipantRepository.getMatchParticipantById(matchParticipantId)
                    ?: return@withContext null
            return@withContext (getMatchById(matchParticipant.matchId))
        }
}