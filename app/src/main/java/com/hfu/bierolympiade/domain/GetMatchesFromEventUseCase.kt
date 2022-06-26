package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.MatchId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMatchesFromEventUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    suspend operator fun invoke(event: Event) = withContext(Dispatchers.Default) {
        event.matches.map{
            matchRepository.getMatchById(MatchId(it))
        }
    }
}