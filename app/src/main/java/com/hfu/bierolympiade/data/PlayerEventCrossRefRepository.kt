package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.game.GameDao
import com.hfu.bierolympiade.data.database.game.gameFromDb
import com.hfu.bierolympiade.data.database.game.gameToDb
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDao
import com.hfu.bierolympiade.data.database.player_event_crossref.playerEventCrossRefToDb
import com.hfu.bierolympiade.domain.model.Game
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.PlayerEventCrossRef
import javax.inject.Inject

class PlayerEventCrossRefRepository @Inject constructor(
    private val dao: PlayerEventCrossRefDao
) {

    //suspend fun getAllGames(): List<Game> = dao.getAll().mapNotNull { gameFromDb(it) }

    //suspend fun getGameById(id: GameId): Game? = dao.getById(id.value)?.let { gameFromDb(it) }

    suspend fun addPlayerEventCrossRef(playerEventCrossRef: PlayerEventCrossRef) {
        dao.insert(playerEventCrossRefToDb(playerEventCrossRef))
    }
}