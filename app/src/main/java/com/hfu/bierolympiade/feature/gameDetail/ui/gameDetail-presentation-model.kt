package com.hfu.bierolympiade.feature.gameDetail.ui

import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.MatchId

class MatchUI(
    val id: MatchId,
    val state: Int,
    val type: Int,
    val playerNamesTeam1: List<String>,
    val playerNamesTeam2: List<String>,
)