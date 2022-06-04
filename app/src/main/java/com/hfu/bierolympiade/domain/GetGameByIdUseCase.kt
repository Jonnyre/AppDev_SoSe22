package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.domain.model.GameId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(
    private val gameRepository: GameRepository
){
    suspend operator fun invoke(gameId: GameId) = withContext(Dispatchers.Default) {
        gameRepository.getGameById(gameId)
    }
}