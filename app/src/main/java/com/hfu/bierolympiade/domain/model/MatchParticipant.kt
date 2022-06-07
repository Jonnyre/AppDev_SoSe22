package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime

@JvmInline
value class MatchParticipantId(val value: String)


class MatchParticipant private constructor(
    val id: MatchParticipantId,
    val matchId: MatchId,
    val playerId: PlayerId,
    val teamId: TeamId,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchParticipant

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: MatchParticipantId,
            matchId: MatchId,
            playerId: PlayerId,
            teamId: TeamId,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): MatchParticipant? {
            return MatchParticipant(id, matchId, playerId, teamId, created, updated, deleted)
        }
    }
}