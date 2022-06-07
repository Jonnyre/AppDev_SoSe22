package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchRepository
import com.hfu.bierolympiade.data.TeamRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import javax.inject.Inject


class AddDemoMatchesUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (matchRepository.getAllMatches().isNotEmpty()) return@withContext

        val matchParticipant = listOf<String>(
            "e32d9cb5-e706-46fb-b6ea-f6afa26f07b2"
        )
        listOfNotNull(
            Match.create(
                id = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
                gameId = GameId("a59c0e7b-3a58-4859-934d-1b0393831637"),
                date = ZonedDateTime.now(),
                type = 0,
                state = 0,
                matchParticipant = matchParticipant
            ),
        ).forEach {
            matchRepository.addMatch(it)
        }
    }
}