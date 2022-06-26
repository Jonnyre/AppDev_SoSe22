package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchParticipantRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDemoMatchParticipants @Inject constructor(
    private val matchParticipantRepository: MatchParticipantRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (matchParticipantRepository.getAllMatchParticipantes().isNotEmpty()) return@withContext

        listOfNotNull(
            MatchParticipant.create(
                id = MatchParticipantId("e32d9cb5-e706-46fb-b6ea-f6afa26f07b2"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("a59c0e7b-3a58-4859-934d-1a0393831637"),
                teamId = TeamId("6e3cd742-61cc-4cf4-a203-b7d9a65b8618")
            ),
            MatchParticipant.create(
                id = MatchParticipantId("0f1c0b27-1bca-444f-ae9d-a38716a498d8"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"),
                teamId = TeamId("6e3cd742-61cc-4cf4-a203-b7d9a65b8618")
            ),
            MatchParticipant.create(
                id = MatchParticipantId("62cc7123-39ae-4d50-ad30-0dc7295043d5"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"),
                teamId = TeamId("8c3568e1-a466-41f6-9255-0ff62350adbd")
            ),
            MatchParticipant.create(
                id = MatchParticipantId("d9d57292-e577-4a03-ae66-5002f06cbff6"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("cc5703cb-b449-48fd-8d6c-6f2446f5cf80"),
                teamId = TeamId("8c3568e1-a466-41f6-9255-0ff62350adbd")
            ),
        ).forEach {
            matchParticipantRepository.addMatchParticipant(it)
        }
    }
}