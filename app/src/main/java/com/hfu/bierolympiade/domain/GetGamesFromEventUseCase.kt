package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGamesFromEventUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(event: Event) = withContext(Dispatchers.Default) {
        event.games.map{
            gameRepository.getGameById(GameId(it))
        }
    }
}