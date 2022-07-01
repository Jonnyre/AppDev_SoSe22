package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteGameByIdUseCase @Inject constructor(
    private val gameRepository: GameRepository
){
    suspend operator fun invoke(id: GameId) = withContext(Dispatchers.Default) {
        gameRepository.deleteGameById(id)
    }
}