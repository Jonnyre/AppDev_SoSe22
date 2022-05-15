package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime
import kotlin.math.max


@JvmInline
value class GameId(val value: String)


class Game private constructor(
    val id: GameId,
    val name: String,
    val status: String,
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
        ): Game? {
            if (name.isBlank()) return null
            val now = ZonedDateTime.now()
            return Game(id, name, status, now, now, now)
        }
    }
}