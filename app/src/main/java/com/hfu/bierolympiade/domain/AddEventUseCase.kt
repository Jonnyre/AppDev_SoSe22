package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.eventRepo
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.feature.addEvent.ui.AddEventUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AddEventUseCase {
    suspend operator fun invoke(addEventUI: AddEventUI): Boolean = withContext(Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newEvent = Event.create(
            EventId(uniqueID),
            name = addEventUI.name,
            date = addEventUI.date,
            location = addEventUI.location,
            participants = 10
        )
        if(newEvent != null)  {
            eventRepo.addEvent(newEvent)
            return@withContext true
        }
        return@withContext false
    }
}