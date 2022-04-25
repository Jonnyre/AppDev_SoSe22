package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.playerRepo

class GetPlayersUseCase {
    operator fun invoke() = playerRepo.getAllPlayers()
}