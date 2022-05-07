package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime

@JvmInline
value class EventId(val value: String)


class Event private constructor(
    val id: EventId,
    val name: String,
    val location: String,
    val date: String,
    val participants: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: EventId,
            name: String,
            location: String,
            date: String,
            participants: Int,
        ): Event? {
            if (name.isBlank()) return null
            val now = ZonedDateTime.now()
            return Event(id, name, location, date, participants, now, now, now)
        }
    }
}