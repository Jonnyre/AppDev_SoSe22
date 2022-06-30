package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchRepository
import com.hfu.bierolympiade.domain.model.MatchId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMatchByIdUseCase @Inject constructor(
    private val matchRepository: MatchRepository
){
    suspend operator fun invoke(id: MatchId) = withContext(Dispatchers.Default){matchRepository.getMatchById(id)}
}