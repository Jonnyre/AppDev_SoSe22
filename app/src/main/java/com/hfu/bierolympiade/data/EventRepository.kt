package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.event.EventDao
import com.hfu.bierolympiade.data.database.event.eventFromDb
import com.hfu.bierolympiade.data.database.event.eventToDb
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val dao: EventDao
) {

    suspend fun getAllEvents(): List<Event> = dao.getAll().mapNotNull { eventFromDb(it) }

    suspend fun getEventById(id: EventId): Event? = dao.getById(id.value)?.let { eventFromDb(it) }

    suspend fun updateEvent(newEvent: Event) {
        dao.upsert(eventToDb(newEvent))
    }

    suspend fun addEvent(event: Event) {
        dao.upsert(eventToDb(event))
    }
}