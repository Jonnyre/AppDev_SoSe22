package com.hfu.bierolympiade.feature.eventDetail.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetEventByIdUseCase
import com.hfu.bierolympiade.domain.GetGamesFromEventUseCase
import com.hfu.bierolympiade.domain.model.EventId
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val GetGamesFromEvent: GetGamesFromEventUseCase,
    private val GetEventById: GetEventByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<GameUI>> = liveData {
        val id = savedStateHandle.get<String>("id").orEmpty()
        val event = GetEventById(EventId(id)) ?: return@liveData
        val result = GetGamesFromEvent(event).map { game ->
            game?.let {
                GameUI(
                    id = it.id,
                    name = game.name,
                    status = game.status,
                )
            }
        }.sortedBy { it?.name }
        emit(result as List<GameUI>)
    }
}