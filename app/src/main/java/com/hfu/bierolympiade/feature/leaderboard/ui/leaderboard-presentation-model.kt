package com.hfu.bierolympiade.feature.leaderboard.ui

import com.hfu.bierolympiade.domain.model.EventId

class LeaderboardUI(
    val eventId: EventId,
    val name: String,
)

class LeaderBoardItemUI(
    val playerName: String,
    val points: Int
)