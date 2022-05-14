package com.hfu.bierolympiade.data.database

import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId


fun eventToDb(event: Event): EventDb = EventDb(
    id = event.id.value,
    name = event.name,
    location = event.location,
    participants = event.participants,
    date = event.date,
    created = event.created,
    updated = event.updated,
    deleted = event.deleted,
)

fun eventFromDb(event: EventDb): Event? {
    return Event.create(
        id = EventId(event.id),
        name = event.name,
        location = event.location,
        participants = event.participants,
        date = event.date,
    )
}
