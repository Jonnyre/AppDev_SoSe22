package com.hfu.bierolympiade.feature.addGame.ui

import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId

class AddGameUI(
    val gameId: GameId?,
    val eventId: EventId,
    val rules: String,
    val teamSize: Int,
    val winCondition: Int,
    val points: Int,
    val isHighScore: Boolean,
)