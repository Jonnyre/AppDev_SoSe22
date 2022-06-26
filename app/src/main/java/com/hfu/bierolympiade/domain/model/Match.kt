package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime

@JvmInline
value class MatchId(val value: String)


class Match private constructor(
    val id: MatchId,
    val eventId: EventId,
    val gameId: GameId,
    val date: ZonedDateTime,
    val type: Int,
    val state: Int,
    val matchParticipant: List<String>,
    val matchScores: List<String>,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Match

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: MatchId,
            eventId: EventId,
            gameId: GameId,
            date: ZonedDateTime,
            state: Int,
            type: Int,
            matchParticipant: List<String>,
            matchScores: List<String>,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): Match? {
            return Match(id, eventId, gameId,date, state, type, matchParticipant, matchScores, created, updated, deleted)
        }
    }
}