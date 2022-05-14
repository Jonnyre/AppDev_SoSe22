package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.playerRepo
import com.hfu.bierolympiade.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlayerByIdUseCase {
    suspend operator fun invoke(id: PlayerId) = withContext(Dispatchers.Default){playerRepo.getPlayerById(id)}
}