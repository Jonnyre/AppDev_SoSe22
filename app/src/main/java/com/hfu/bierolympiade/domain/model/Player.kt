package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime


@JvmInline
value class PlayerId(val value: String)


class Player private constructor(
    val id: PlayerId,
    val name: String,
    val description: String,
    val events: List<String>,
    val matchScores: List<String>,
    val matchParticipants: List<String>,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: PlayerId,
            name: String,
            description: String,
            events: List<String>,
            matchScores: List<String>,
            matchParticipants: List<String>,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): Player? {
            if (name.isBlank()) return null
            return Player(id, name, description, events, matchScores, matchParticipants, created, updated, deleted)
        }
    }
}