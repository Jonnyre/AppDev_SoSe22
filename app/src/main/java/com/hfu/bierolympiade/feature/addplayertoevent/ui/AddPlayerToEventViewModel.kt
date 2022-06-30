package com.hfu.bierolympiade.feature.addplayertoevent.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.hfu.bierolympiade.domain.*
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.PlayerId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddPlayerToEventViewModel @Inject constructor(
    private val GetPlayers: GetPlayersUseCase,
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val deleteAllFromEventIdUseCase: DeleteAllFromEventIdUseCase,
    private val addPlayerToEvent: AddPlayerToEventUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<AddPlayerToEventUI>> = liveData {
        val result = GetPlayers().map { player ->
            AddPlayerToEventUI(
                id = player.id,
                eventId = EventId(savedStateHandle.get<String>("id")?:""),
                name = player.name,
            )
        }.sortedBy { it.name }
        emit(result)
    }
    fun addPlayersToEvent(playerIdList: List<String>){
        for(playerId in playerIdList){
            viewModelScope.launch {
                addPlayerToEvent(EventId(savedStateHandle.get<String>("id")?:""),PlayerId(playerId))
            }
        }
    }

    fun getEventId() = savedStateHandle.get<String>("id")?: ""

    fun getEventById(): Event?{
        return runBlocking {
            getEventByIdUseCase(EventId(getEventId()))
        }
    }

    fun removeAllFromThisEvent(){
        viewModelScope.launch { deleteAllFromEventIdUseCase(EventId(getEventId()))}
    }
}