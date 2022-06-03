package com.hfu.bierolympiade.feature.event.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import com.hfu.bierolympiade.domain.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val GetEvents: GetEventsUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<EventUI>> = liveData {
        val result = GetEvents().map { event ->
                EventUI(
                    id = event.id,
                    name = event.name,
                    location = event.location,
                    date = event.date,
                )
            }.sortedBy { it.name }
        emit(result)
    }
}