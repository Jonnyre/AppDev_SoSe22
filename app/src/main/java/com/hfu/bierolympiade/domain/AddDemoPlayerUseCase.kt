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
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929c2"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a3"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929c4"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a5"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929c6"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf800711"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf800712"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b292913"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf800714"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b292915"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf800716"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b292917"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf800718"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b292919"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf800720"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
            Player.create(
                id = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b292921"),
                name = "Jonathan Rißler",
                description = "Champion",
            ),
            Player.create(
                id = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf800722"),
                name = "Peter Pan",
                description = "Ungeschlagener Zwischenkotzer",
            ),
        ).forEach {
            playerRepository.addPlayer(it)
        }
    }
}