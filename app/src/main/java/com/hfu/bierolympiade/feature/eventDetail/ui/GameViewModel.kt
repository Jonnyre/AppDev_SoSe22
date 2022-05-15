package com.hfu.bierolympiade.feature.eventDetail.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetGamesUseCase


class GameViewModel : ViewModel() {
    fun bindUi(context: Context): LiveData<List<GameUI>> = liveData {
        val result = GetGamesUseCase()().map { game ->
            GameUI(
                id = game.id,
                name = game.name,
                status = game.status,
            )
        }.sortedBy { it.name }
        emit(result)
    }
}