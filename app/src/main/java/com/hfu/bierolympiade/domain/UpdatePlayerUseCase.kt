package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.PlayerRepository
import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdatePlayerUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    suspend operator fun invoke(playerId: PlayerId, name: String, description: String): Boolean = withContext(
        Dispatchers.Default) {
        val newPlayer = Player.create(
            id = playerId,
            name = name,
            description = description,
            matchScores = emptyList(),
            events = emptyList(),
            matchParticipants = emptyList()
        )
        if(newPlayer != null)  {
            playerRepository.updatePlayer(newPlayer)
            return@withContext true
        }
        return@withContext false
    }
}