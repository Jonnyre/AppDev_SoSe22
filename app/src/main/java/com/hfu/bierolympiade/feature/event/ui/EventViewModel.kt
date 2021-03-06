package com.hfu.bierolympiade.feature.event.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetEventsWithoutTemporaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val GetEventsWithoutTemporary: GetEventsWithoutTemporaryUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<EventUI>> = liveData {
        val result = GetEventsWithoutTemporary().map { event ->
                EventUI(
                    id = event.id,
                    name = event.name,
                    location = event.location,
                    date = event.date,
                    participant = event.players.count()
                )
            }.sortedBy { it.name }
        emit(result)
    }
}