package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.MatchScoreRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDemoMatchScoresUseCase @Inject constructor(
    private val matchScoreRepository: MatchScoreRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (matchScoreRepository.getAllMatchScores().isNotEmpty()) return@withContext

        listOfNotNull(
            MatchScore.create(
                id = MatchScoreId("8fc6e5e4-7127-4a78-80a4-d56f3e9d6971"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("a59c0e7b-3a58-4859-934d-1a0393831637"),
                teamId = TeamId("6e3cd742-61cc-4cf4-a203-b7d9a65b8618"),
                value = 0
            ),
            MatchScore.create(
                id = MatchScoreId("01066805-09fc-405d-b81a-b37a1ad916f5"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"),
                teamId =TeamId("6e3cd742-61cc-4cf4-a203-b7d9a65b8618"),
                value = 0
            ),
            MatchScore.create(
                id = MatchScoreId("3d63e0f9-33b7-49e9-a3d5-57513ad538d3"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"),
                teamId =TeamId("8c3568e1-a466-41f6-9255-0ff62350adbd"),
                value = 0
            ),
            MatchScore.create(
                id = MatchScoreId("cd072fb0-00fb-44de-aeff-9f6e659c3284"),
                matchId = MatchId("382d594e-be8e-4025-896c-877a04b068b3"),
                playerId = PlayerId("cc5703cb-b449-48fd-8d6c-6f2446f5cf80"),
                teamId =TeamId("8c3568e1-a466-41f6-9255-0ff62350adbd"),
                value = 0
            ),
        ).forEach {
            matchScoreRepository.addMatchScore(it)
        }
    }
}