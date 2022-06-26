package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameTypeRepository
import com.hfu.bierolympiade.domain.model.GameType
import com.hfu.bierolympiade.domain.model.GameTypeId
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
            ),
            GameType.create(
                gameTypeId = GameTypeId("a59c0k7b-3a58-4859-934d-1b0393821637"),
                name = "Bierpong",
                icon = "ressource1",
                rules = "Werfen und saufen"
            ),
            GameType.create(
                gameTypeId = GameTypeId("a59c0k9b-3a58-4859-934d-1b0393821637"),
                name = "Bierquiz",
                icon = "ressource1",
                rules = "Denken und saufen"
            ),
            GameType.create(
                gameTypeId = GameTypeId("a59c0k9b-3a58-4859-934p-1b0313821637"),
                name = "Flip Cup",
                icon = "ressource1",
                rules = "Flippen und saufen"
            ),
            GameType.create(
                gameTypeId = GameTypeId("a59c0k5b-3a58-4859-935d-1b0393821637"),
                name = "Hinderniss Lauf",
                icon = "ressource1",
                rules = "Laufen Springen und saufen"
            )

        ).forEach {
            gameTypeRepository.addGameType(it)
        }
    }
}