package com.hfu.bierolympiade.feature.gameDetail.ui

import com.hfu.bierolympiade.domain.model.MatchId
import com.hfu.bierolympiade.domain.model.TeamId

class MatchUI(
    val id: MatchId,
    val state: Int,
    val type: Int,
    val winCondition: Int,
    val playerNamesTeam1: List<String>,
    val playerNamesTeam2: List<String>,
    val team1: TeamId,
    val team2: TeamId,
    val score1: Int,
    val score2: Int,
)