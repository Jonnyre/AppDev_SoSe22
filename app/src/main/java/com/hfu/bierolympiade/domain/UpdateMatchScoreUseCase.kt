package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchScoreRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMatchScoreUseCase @Inject constructor(
    private val matchScoreRepository: MatchScoreRepository
) {
    suspend operator fun invoke(matchScore: MatchScore, value: Int): Boolean = withContext(
        Dispatchers.Default) {
        val newMatchScore = MatchScore.create(
            id = matchScore.id,
            matchId = matchScore.matchId,
            teamId = matchScore.teamId,
            playerId = matchScore.playerId,
            value = value
        )
        if(newMatchScore != null)  {
            matchScoreRepository.updateMatchScore(newMatchScore)
            return@withContext true
        }
        return@withContext false
    }
}