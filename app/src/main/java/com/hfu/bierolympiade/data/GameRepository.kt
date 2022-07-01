package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.game.GameDao
import com.hfu.bierolympiade.data.database.game.gameFromDb
import com.hfu.bierolympiade.data.database.game.gameToDb
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val dao: GameDao
) {

    /*private val allGames = listOf(
        Game.create(
            id = GameId("a59c0e7b-3a58-4859-934d-1b0393831637"),
            name = "Flunkyball",
            status = "On Going",
            matches = emptyList(),
            eventId =
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
    ).filterNotNull()*/

    suspend fun getAllGames(): List<Game> = dao.getAll().mapNotNull { gameFromDb(it) }

    suspend fun getGameById(id: GameId): Game? = dao.getById(id.value)?.let { gameFromDb(it) }

    suspend fun addGame(game: Game) {
        dao.insert(gameToDb(game))
    }

    suspend fun deleteGameById(id: GameId) = dao.deleteByGameId(id.value)
}