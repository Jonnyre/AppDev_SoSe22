package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchScoreRepository
import com.hfu.bierolympiade.domain.model.MatchScoreId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMatchScoreByIdUseCase @Inject constructor(
    private val matchScoreRepository: MatchScoreRepository
){
    suspend operator fun invoke(id: MatchScoreId) = withContext(Dispatchers.Default){matchScoreRepository.getMatchScoreById(id)}
}