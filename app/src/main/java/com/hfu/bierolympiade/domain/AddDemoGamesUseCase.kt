package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/* class AddDemoGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (gameRepository.getAllGames().isNotEmpty()) return@withContext

        listOfNotNull(
            Game.create(
                id = GameId("a59c0e7b-3a58-4859-934d-1b0393831637"),
                name = "Flunkyball",
                status = "On Going",
            ),
            Game.create(
                id = GameId("867e5af2-aa53-4e46-9cfd-a1bc982929cb"),
                name = "Flip Cup",
                status = "Finished",
            ),
            Game.create(
                id = GameId("f16cdf15-6528-6a0b-993c-24d5bf8007a7"),
                name = "Bierpong",
                status = "Not yet started",

            ),
        ).forEach {
            gameRepository.addGame(it)
        }
    }
} */