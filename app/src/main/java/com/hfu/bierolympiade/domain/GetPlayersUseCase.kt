package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.playerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlayersUseCase {
    suspend operator fun invoke() = withContext(Dispatchers.Default) { playerRepo.getAllPlayers()}
}