package com.hfu.bierolympiade.feature.eventDetail.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val GetGames: GetGamesUseCase
): ViewModel() {
    fun bindUi(context: Context): LiveData<List<GameUI>> = liveData {
        val result = GetGames().map { game ->
            GameUI(
                id = game.id,
                name = game.name,
                status = game.status,
            )
        }.sortedBy { it.name }
        emit(result)
    }
}