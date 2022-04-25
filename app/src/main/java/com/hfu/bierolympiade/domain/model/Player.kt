package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime
import kotlin.math.max


@JvmInline
value class PlayerId(val value: String)


class Player private constructor(
    val id: PlayerId,
    val name: String,
    val description: String,
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
        ): Player? {
            if (name.isBlank()) return null
            val now = ZonedDateTime.now()
            return Player(id, name, description, now, now, now)
        }
    }
}