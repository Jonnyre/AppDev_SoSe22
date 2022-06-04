package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetAllGameTypesUseCase @Inject constructor(
    private val gameTypesRepository: GameTypeRepository
){
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        gameTypesRepository.getAllGameTypes()
    }
}