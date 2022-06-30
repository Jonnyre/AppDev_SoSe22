package com.hfu.bierolympiade.data.database.player

import com.hfu.bierolympiade.domain.model.Player
import com.hfu.bierolympiade.domain.model.PlayerId

fun playerToDb(player: Player): PlayerDb = PlayerDb(
    playerId = player.id.value,
    name = player.name,
    description = player.description,
    created = player.created,
    updated = player.updated,
    deleted = player.deleted,
)

fun playerFromDb(playerWithEventAndMatches: PlayerWithEventAndMatchScoreAndMatches): Player? {
    return Player.create(
        id = PlayerId(playerWithEventAndMatches.player.player.player.playerId),
        name = playerWithEventAndMatches.player.player.player.name,
        description = playerWithEventAndMatches.player.player.player.description,
        events = playerWithEventAndMatches.player.player.events.mapNotNull { it.eventId },
        matchScores = playerWithEventAndMatches.player.matchScores.mapNotNull { it.matchScoreId },
        matchParticipants = playerWithEventAndMatches.matchParticipants.mapNotNull { it.matchParticipantId }
    )
}