package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime


@JvmInline
value class GameId(val value: String)


class Game private constructor(
    val id: GameId,
    val name: String,
    val icon: String,
    val rules: String,
    val status: String,
    val matches: List<String>,
    val eventId: EventId,
    val gameTypeId: GameTypeId,
    val teamSize: Int,
    val winCondition: Int,
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
            icon: String,
            rules: String,
            status: String,
            matches: List<String>,
            eventId: EventId,
            gameTypeId: GameTypeId,
            teamSize: Int,
            winCondition: Int,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): Game? {
            if (gameTypeId.value.isBlank()) return null
            return Game(id, name, icon, rules, status, matches, eventId, gameTypeId, teamSize, winCondition,created, updated, deleted)
        }

        fun create(
            id: GameId,
            status: String,
            matches: List<String>,
            eventId: EventId,
            gameTypeId: GameTypeId,
            teamSize: Int,
            winCondition: Int,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): Game? {
            if (gameTypeId.value.isBlank()) return null
            return Game(id, name = "", icon = "", rules = "", status, matches, eventId, gameTypeId, teamSize, winCondition,created, updated, deleted)
        }
    }

}