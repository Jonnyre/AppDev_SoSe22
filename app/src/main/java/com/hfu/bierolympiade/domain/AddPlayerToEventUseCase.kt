package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchRepository
import com.hfu.bierolympiade.data.PlayerEventCrossRefRepository
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject

class AddPlayerToEventUseCase @Inject constructor(
    private val playerEventCrossRefRepository: PlayerEventCrossRefRepository
) {
    suspend operator fun invoke(eventId: EventId, playerId: PlayerId): Boolean = withContext(
        Dispatchers.Default
    ) {
        val newPlayerEventCrossRef = PlayerEventCrossRef.create(
            eventId = eventId,
            playerId = playerId
        )
        if (newPlayerEventCrossRef != null) {
            playerEventCrossRefRepository.addPlayerEventCrossRef(newPlayerEventCrossRef)
            return@withContext true
        }
        return@withContext false
    }
}