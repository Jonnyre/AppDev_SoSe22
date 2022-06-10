package com.hfu.bierolympiade.feature.addplayertoevent.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfu.bierolympiade.domain.AddPlayerToEventUseCase
import com.hfu.bierolympiade.domain.GetPlayersUseCase
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.PlayerId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddPlayerToEventViewModel @Inject constructor(
    private val GetPlayers: GetPlayersUseCase,
    private val addPlayerToEvent: AddPlayerToEventUseCase,
    private val savedStateHandle: SavedStateHandle
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
    fun addPlayersToEvent(playerIdList: List<String>){
        for(playerId in playerIdList){
            viewModelScope.launch {
                Timber.log(Log.INFO, "EventId" + savedStateHandle.get<String>("id"))
                addPlayerToEvent(EventId(savedStateHandle.get<String>("id")?:""),PlayerId(playerId))
            }
        }
    }
}