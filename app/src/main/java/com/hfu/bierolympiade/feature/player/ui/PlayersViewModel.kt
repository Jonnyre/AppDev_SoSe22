package com.hfu.bierolympiade.feature.player.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetEventsUseCase
import com.hfu.bierolympiade.domain.GetPlayersUseCase
import com.hfu.bierolympiade.feature.event.ui.EventUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val GetPlayers: GetPlayersUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<PlayerUI>> = liveData {
        val result = GetPlayers().map { player ->
            PlayerUI(
                id = player.id,
                name = player.name,
                description = player.description,
            )
        }.sortedBy { it.name }
        emit(result)
    }
}