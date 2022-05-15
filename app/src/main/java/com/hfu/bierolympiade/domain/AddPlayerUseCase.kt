package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.playerRepo
import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AddPlayerUseCase {
    suspend operator fun invoke(name: String, music: String, description: String): Boolean = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newPlayer = Player.create(
            PlayerId(uniqueID),
            name = name,
            description = description
        )
        if(newPlayer != null)  {
            playerRepo.addPlayer(newPlayer)
            return@withContext true
        }
        return@withContext false
    }
}