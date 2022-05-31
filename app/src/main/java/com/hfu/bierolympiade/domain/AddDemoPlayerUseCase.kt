package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.PlayerRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDemoPlayerUseCase @Inject constructor(
    private val playerRepository: PlayerRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (playerRepository.getAllPlayers().isNotEmpty()) return@withContext

        listOfNotNull(
            Player.create(
                id = PlayerId("a59c0e7b-3a58-4859-934d-1a0393831637"),
                name = "Felix Lütte",
                description = "Absoluter Versager",
                events = emptyList()
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"),
                name = "Jonathan Rißler",
                description = "Champion",
                events = emptyList()
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
                events = emptyList()
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929c2"),
                name = "Jonathan Rißler",
                description = "Champion",
                events = emptyList()
            ),
        ).forEach {
            playerRepository.addPlayer(it)
        }
    }
}