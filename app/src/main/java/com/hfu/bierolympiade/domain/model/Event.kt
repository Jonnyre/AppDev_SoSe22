package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime

@JvmInline
value class EventId(val value: String)


class Event private constructor(
    val id: EventId,
    val name: String,
    val location: String,
    val date: String,
    val fees: Int?,
    val isTemporary: Boolean,
    val matches: List<String>,
    val games: List<String>,
    val players: List<String>,
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
            fees: Int?,
            isTemporary: Boolean,
            matches: List<String>,
            games: List<String>,
            players: List<String>,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): Event? {
            return Event(id, name, location, date, fees, isTemporary, matches, games, players, created, updated, deleted)
        }
    }
}