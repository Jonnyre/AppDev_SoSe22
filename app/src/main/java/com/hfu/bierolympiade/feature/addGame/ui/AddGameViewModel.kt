package com.hfu.bierolympiade.feature.addGame.ui

import android.content.Context
import androidx.lifecycle.*
import com.hfu.bierolympiade.domain.AddGameUseCase
import com.hfu.bierolympiade.domain.GetGameTypeByIdUseCase
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameTypeId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGameViewModel @Inject constructor(
    val getGameTypeById: GetGameTypeByIdUseCase,
    val addGame: AddGameUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun bindUi(context: Context): LiveData<AddGameUI> = liveData {
        val result = getGameTypeById(GameTypeId(savedStateHandle.get("gameTypeId")?:""))?.rules?.let {
            AddGameUI(EventId(savedStateHandle.get("eventId")?:"") ,
                it
            )
        }
        if (result != null) {
            emit(result)
        }
    }

    fun onSaveGame(teamSize: Int, winCondition: Int, points: Int, rules: String) {
        val eventId = EventId(savedStateHandle.get("eventId")?: "")
        viewModelScope.launch {
            addGame(status = "notStarted", points =  points, eventId = eventId, winCondition = winCondition, teamSize = teamSize, rules = rules, gameTypeId = GameTypeId(savedStateHandle.get("gameTypeId")?:""))
        }
    }
}