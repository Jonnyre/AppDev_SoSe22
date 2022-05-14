package com.hfu.bierolympiade.feature.event.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import com.hfu.bierolympiade.domain.GetEventsUseCase

class EventViewModel : ViewModel() {
    fun bindUi(context: Context): LiveData<List<EventUI>> = liveData {
        val result = GetEventsUseCase()().map { event ->
                EventUI(
                    id = event.id,
                    name = event.name,
                    location = event.location,
                    date = event.date,
                    participants = event.participants
                )
            }.sortedBy { it.name }
        emit(result)
    }
}