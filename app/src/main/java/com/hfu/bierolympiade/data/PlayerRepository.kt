package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId


val playerRepo = PlayerRepository()

class PlayerRepository {

    private val allPlayers = listOf(
        Player.create(
            id = PlayerId("a59c0e7b-3a58-4859-934d-1a0393831637"),
            name = "Felix Lütte",
            description = "Absoluter Versager",
        ),
        Player.create(
            id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"),
            name = "Jonathan Rißler",
            description = "Champion",
        ),
        Player.create(
            id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"),
            name = "Peter Pan",
            description = "Ungeschlagener Zwischenkotzer",
        ),
    ).filterNotNull()

    fun getAllPlayers() = allPlayers


    fun getPlayerById(id: PlayerId): Player? = allPlayers.firstOrNull {
        it.id == id
    }
}