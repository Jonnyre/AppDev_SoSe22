package com.hfu.bierolympiade.feature.addplayertoevent.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPlayerToEventViewModel @Inject constructor(
    private val GetPlayers: GetPlayersUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<AddPlayerToEventUI>> = liveData {
        val result = GetPlayers().map { player ->
            AddPlayerToEventUI(
                id = player.id,
                name = player.name,
            )
        }.sortedBy { it.name }
        emit(result)
    }
}