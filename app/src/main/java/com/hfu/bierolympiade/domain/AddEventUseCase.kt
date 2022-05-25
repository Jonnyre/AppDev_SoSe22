package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke(name: String, location: String, date: String, fees: Int): Boolean = withContext(Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newEvent = Event.create(
            EventId(uniqueID),
            name = name,
            date = date,
            location = location,
            participants = 10,
            fees = fees
        )
        if(newEvent != null)  {
            eventRepository.addEvent(newEvent)
            return@withContext true
        }
        return@withContext false
    }
}