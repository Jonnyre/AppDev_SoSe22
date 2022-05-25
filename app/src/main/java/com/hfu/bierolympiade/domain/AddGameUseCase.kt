package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


class AddGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(name: String, status: String): Boolean = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newGame = Game.create(
            GameId(uniqueID),
            name = name,
            status = status
        )
        if(newGame != null)  {
            gameRepository.addGame(newGame)
            return@withContext true
        }
        return@withContext false
    }
}