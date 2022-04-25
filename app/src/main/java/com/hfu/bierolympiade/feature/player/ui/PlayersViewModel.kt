package com.hfu.bierolympiade.feature.player.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfu.bierolympiade.domain.GetPlayersUseCase



class PlayersViewModel : ViewModel() {
    fun bindUi(context: Context): LiveData<List<PlayerUI>> {
        val state = MutableLiveData(
            GetPlayersUseCase()().map { player ->
                PlayerUI(
                    id = player.id,
                    name = player.name,
                    description = player.description,
                )
            }.sortedBy { it.name }
        )
        return state
    }
}