package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.data.GameTypeRepository
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.GameTypeId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDemoGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val gameTypeRepository: GameTypeRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (gameRepository.getAllGames().isNotEmpty()) return@withContext

        listOfNotNull(
            Game.create(
                id = GameId("a59c0e7b-3a58-4859-934d-1b0393831637"),
                gameTypeId = GameTypeId("a59c0e7b-3a58-4859-934d-1b0393821637"),
                status = "On Going",
                matches = emptyList(),
                eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
                teamSize = 10,
                winCondition = 10,
                rules = ""
            ),
            Game.create(
                id = GameId("867e5af2-aa53-4e46-9cfd-a1bc982929cb"),
                gameTypeId = GameTypeId("a59c0e7b-3a58-4859-934d-1b0393821637"),
                status = "Finished",
                matches = emptyList(),
                eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
                teamSize = 6,
                winCondition = 6,
                rules = ""
            ),
            Game.create(
                id = GameId("f16cdf15-6528-6a0b-993c-24d5bf8007a7"),
                gameTypeId = GameTypeId("a59c0e7b-3a58-4859-934d-1b0393821637"),
                status = "Not yet started",
                matches = emptyList(),
                eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
                teamSize = 4,
                winCondition = 4,
                rules = ""
            ),
            Game.create(
                id = GameId("593eebe4-c4a9-4834-a2b3-fed59f4b65a8"),
                gameTypeId = GameTypeId("a59c0e7b-3a58-4859-934d-1b0393821637"),
                status = "Not yet started",
                matches = emptyList(),
                eventId = EventId("a6dc7e9e-d5a2-4f96-93b1-349aa24395aa"),
                teamSize = 4,
                winCondition = 4,
                rules = ""
            ),
        ).forEach {
            gameRepository.addGame(it)
        }
    }
}