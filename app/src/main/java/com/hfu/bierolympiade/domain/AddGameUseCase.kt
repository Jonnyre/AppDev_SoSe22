package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.GameTypeId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


class AddGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(name: String, status: String, gameTypeId: GameTypeId): Boolean = withContext(
        Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newGame = Game.create(
            GameId(uniqueID),
            gameTypeId = gameTypeId,
            status = status,
            // TODO Parameter übergeben
            eventId = EventId("ösdjhgfljkdsjgflkdjg"),
            matches = emptyList(),
        )
        if(newGame != null)  {
            gameRepository.addGame(newGame)
            return@withContext true
        }
        return@withContext false
    }
}