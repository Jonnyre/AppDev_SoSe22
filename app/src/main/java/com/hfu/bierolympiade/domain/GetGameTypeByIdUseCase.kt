package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameTypeRepository
import com.hfu.bierolympiade.domain.model.GameTypeId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGameTypeByIdUseCase @Inject constructor(
    private val gameTypeRepository: GameTypeRepository
){
    suspend operator fun invoke(id: GameTypeId) = withContext(Dispatchers.Default){gameTypeRepository.getGameTypeById(id)}
}