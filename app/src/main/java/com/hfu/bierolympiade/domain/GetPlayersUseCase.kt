package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
){
    suspend operator fun invoke() = withContext(Dispatchers.Default) { playerRepository.getAllPlayers()}
}