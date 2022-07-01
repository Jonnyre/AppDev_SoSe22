package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.GameRepository
import com.hfu.bierolympiade.data.PlayerRepository
import com.hfu.bierolympiade.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import javax.inject.Inject

class UpdateGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(gameId: GameId, name: String, icon: String, rules: String, status: String, matches: List<String>, eventId: EventId, gameTypeId: GameTypeId, teamSize: Int, winCondition: Int, points: Int, created: ZonedDateTime, deleted: ZonedDateTime): Boolean = withContext(
        Dispatchers.Default
    ) {
        val newGame = Game.create(
            id = gameId,
            name = name,
            icon = icon,
            rules = rules,
            matches = matches,
            status = status,
            eventId = eventId,
            gameTypeId = gameTypeId,
            teamSize = teamSize,
            winCondition = winCondition,
            points = points,
            created = created,
            deleted = deleted,
        )
        if(newGame != null){
            gameRepository.updateGame(newGame)
            return@withContext true
        }

        return@withContext false
    }
}