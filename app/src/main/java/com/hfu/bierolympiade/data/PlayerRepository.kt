package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.player.PlayerDao
import com.hfu.bierolympiade.data.database.player.playerFromDb
import com.hfu.bierolympiade.data.database.player.playerToDb
import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId
import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val dao: PlayerDao
) {

    suspend fun getAllPlayers(): List<Player> = dao.getAll().mapNotNull { playerFromDb(it) }

    suspend fun getPlayerById(id: PlayerId): Player? = dao.getById(id.value)?.let { playerFromDb(it) }

    suspend fun addPlayer(player: Player) {
        dao.insert(playerToDb(player))
    }
}