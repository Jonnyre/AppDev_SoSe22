package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.game.GameDao
import com.hfu.bierolympiade.data.database.game.gameFromDb
import com.hfu.bierolympiade.data.database.game.gameToDb
import com.hfu.bierolympiade.data.database.gameType.GameTypeDao
import com.hfu.bierolympiade.data.database.gameType.gameTypeFromDb
import com.hfu.bierolympiade.data.database.gameType.gameTypeToDb
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.GameType
import javax.inject.Inject

class GameTypeRepository @Inject constructor(
    private val dao: GameTypeDao
) {

    suspend fun getAllGameTypes(): List<GameType> = dao.getAll().mapNotNull { gameTypeFromDb(it) }

    suspend fun getGameTypeById(id: GameId): GameType? = dao.getById(id.value)?.let { gameTypeFromDb(it) }

    suspend fun addGameType(gameType: GameType) {
        dao.insert(gameTypeToDb(gameType))
    }
}