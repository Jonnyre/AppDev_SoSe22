package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.PlayerRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPlayersForEventUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    suspend operator fun invoke(event: Event) = withContext(Dispatchers.Default) {
        event.players.map{
            playerRepository.getPlayerById(PlayerId(it))
        }
    }
}