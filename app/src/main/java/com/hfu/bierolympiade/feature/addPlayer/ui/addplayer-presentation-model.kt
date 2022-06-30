package com.hfu.bierolympiade.feature.addPlayer.ui

import com.hfu.bierolympiade.domain.model.PlayerId

class AddPlayerUI(
    val id: PlayerId?,
    val name: String,
    val music: String,
    val description: String,
    val winningMatches: Int,
    val losingMatches: Int
)