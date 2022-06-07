package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject


class AddMatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    suspend operator fun invoke(eventId: EventId, gameId: GameId): Boolean = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newMatch = Match.create(
            MatchId(uniqueID),
            eventId = eventId,
            gameId = gameId,
            type = 0,
            state = 0,
            date = ZonedDateTime.now(),
            matchParticipant = emptyList()
        )
        if(newMatch != null)  {
            matchRepository.addMatch(newMatch)
            return@withContext true
        }
        return@withContext false
    }
}