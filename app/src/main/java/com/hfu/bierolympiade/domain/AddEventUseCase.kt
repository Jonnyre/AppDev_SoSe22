package com.hfu.bierolympiade.domain

import android.util.Log
import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AddEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke(name: String, location: String, date: String, fees: Int, isTemporary: Boolean): String? = withContext(Dispatchers.Default) {
        val uniqueID: String = UUID.randomUUID().toString()
        val newEvent = Event.create(
            EventId(uniqueID),
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
            eventRepository.addEvent(newEvent)
            Timber.log(Log.INFO, "EventIdAddEvent $uniqueID")
            return@withContext uniqueID
        }
        return@withContext null
    }
}