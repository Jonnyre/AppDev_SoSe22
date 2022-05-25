package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.PlayerRepository
import com.hfu.bierolympiade.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPlayerByIdUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
){
    suspend operator fun invoke(id: PlayerId) = withContext(Dispatchers.Default){playerRepository.getPlayerById(id)}
}