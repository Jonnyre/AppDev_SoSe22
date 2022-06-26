package com.hfu.bierolympiade.feature.pickGameType.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetAllGameTypesUseCase
import com.hfu.bierolympiade.domain.model.EventId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class pickGameTypeViewModel @Inject constructor(
    private val GetAllGameTypes: GetAllGameTypesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<pickGameTypeUI>> = liveData {
    val result = GetAllGameTypes()
            .map {
                pickGameTypeUI(
                    id = it.gameTypeId,
                    eventId = EventId(savedStateHandle.get("eventId")?:""),
                    name = it.name,
                    icon = it.icon,
                )
            }
        emit(result)
    }
}