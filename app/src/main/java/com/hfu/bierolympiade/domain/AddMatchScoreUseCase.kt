package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchScoreRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddMatchScoreUseCase @Inject constructor(
    private val matchScoreRepository: MatchScoreRepository
) {
    suspend operator fun invoke(matchId: MatchId, teamId: TeamId, playerId: PlayerId, value: Int): String? = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newMatchScore = MatchScore.create(
            MatchScoreId(uniqueID),
            matchId = matchId,
            teamId = teamId,
            playerId = playerId,
            value = value
        )
        if(newMatchScore != null)  {
            matchScoreRepository.addMatchScore(newMatchScore)
            return@withContext uniqueID
        }
        return@withContext null
    }
}