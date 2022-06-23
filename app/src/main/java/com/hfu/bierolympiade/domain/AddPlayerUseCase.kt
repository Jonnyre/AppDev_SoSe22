package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.PlayerRepository
import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddPlayerUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    suspend operator fun invoke(name: String, music: String, description: String): Boolean = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newPlayer = Player.create(
            PlayerId(uniqueID),
            name = name,
            description = description,
            events = emptyList(),
            matchScores = emptyList()
        )
        if(newPlayer != null)  {
            playerRepository.addPlayer(newPlayer)
            return@withContext true
        }
        return@withContext false
    }
}