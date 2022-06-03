package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime
import kotlin.math.max


@JvmInline
value class GameId(val value: String)


class Game private constructor(
    val id: GameId,
    val name: String,
    val status: String,
    val matches: List<String>,
    val eventId: EventId,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: GameId,
            name: String,
            status: String,
            matches: List<String>,
            eventId: EventId,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): Game? {
            if (name.isBlank()) return null
            return Game(id, name, status, matches, eventId, created, updated, deleted)
        }
    }
}