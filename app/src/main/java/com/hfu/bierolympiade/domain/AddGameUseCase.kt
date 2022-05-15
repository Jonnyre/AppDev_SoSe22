package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.gameRepo
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


class AddGameUseCase {
    suspend operator fun invoke(name: String, status: String): Boolean = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newGame = Game.create(
            GameId(uniqueID),
            name = name,
            status = status
        )
        if(newGame != null)  {
            gameRepo.addGame(newGame)
            return@withContext true
        }
        return@withContext false
    }
}