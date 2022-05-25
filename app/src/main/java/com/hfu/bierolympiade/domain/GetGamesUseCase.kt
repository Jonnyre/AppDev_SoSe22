package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) { gameRepository.getAllGames()}
}