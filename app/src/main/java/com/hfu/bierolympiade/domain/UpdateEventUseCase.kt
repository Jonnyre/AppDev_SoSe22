package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke(eventId: EventId, name: String, location: String, date: String, fees: Int, isTemporary: Boolean): Boolean = withContext(
        Dispatchers.Default) {
        val newEvent = Event.create(
            id = eventId,
            name = name,
            date = date,
            location = location,
            fees = fees,
            matches = emptyList(),
            isTemporary = isTemporary,
            players = emptyList(),
            games = emptyList()
        )
        if(newEvent != null)  {
            eventRepository.updateEvent(newEvent)
            return@withContext true
        }
        return@withContext false
    }
}