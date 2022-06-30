package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.MatchParticipantId
import com.hfu.bierolympiade.domain.model.MatchScoreId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWinnerMatchScoresFromMatchParticipantUseCase @Inject constructor(
    private val getMatchFromMatchParticipant: GetMatchFromMatchParticipantUseCase,
    private val getMatchScoreById: GetMatchScoreByIdUseCase,
    private val getGameById: GetGameByIdUseCase
) {
    suspend operator fun invoke(
        matchParticipantIds: List<MatchParticipantId>
    ) = withContext(
        Dispatchers.Default
    ) {
        val matches = matchParticipantIds.mapNotNull {
            getMatchFromMatchParticipant(it)
        }
        matches.map { match ->
            val matchScores =
                match?.matchScores?.mapNotNull { getMatchScoreById(MatchScoreId(it)) }
                val game = getGameById(match.gameId)
                matchScores?.filter { score -> score.value == game?.winCondition ?: 0 }
        }
    }
}