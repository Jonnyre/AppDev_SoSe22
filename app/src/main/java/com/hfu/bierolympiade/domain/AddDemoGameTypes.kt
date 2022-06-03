package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.data.GameTypeRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDemoGameTypes @Inject constructor(
    private val gameTypeRepository: GameTypeRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (gameTypeRepository.getAllGameTypes().isNotEmpty()) return@withContext

        listOfNotNull(
            GameType.create(
                gameTypeId = GameTypeId("a59c0e7b-3a58-4859-934d-1b0393821637"),
                name = "Flunkyball",
                icon = "ressource1",
                rules = "Laufen und saufen"
            )
        ).forEach {
            gameTypeRepository.addGameType(it)
        }
    }
}