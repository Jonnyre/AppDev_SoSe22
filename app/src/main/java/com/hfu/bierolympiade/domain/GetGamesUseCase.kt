package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.gameRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetGamesUseCase {
    suspend operator fun invoke() = withContext(Dispatchers.Default) { gameRepo.getAllGames()}
}