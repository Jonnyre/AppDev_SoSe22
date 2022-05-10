package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.playerRepo
import com.hfu.bierolympiade.domain.model.PlayerId

class GetPlayerByIdUseCase {
    operator fun invoke(id: PlayerId) = playerRepo.getPlayerById(id)
}