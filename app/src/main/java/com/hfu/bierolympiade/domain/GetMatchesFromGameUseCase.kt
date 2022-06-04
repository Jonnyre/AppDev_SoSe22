package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchRepository
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.MatchId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMatchesFromGameUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    suspend operator fun invoke(game: Game) = withContext(Dispatchers.Default) {
        game.matches.map{
            matchRepository.getMatchById(MatchId(it))
        }
    }
}