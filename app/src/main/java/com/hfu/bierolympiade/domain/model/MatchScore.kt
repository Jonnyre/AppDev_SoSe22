package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime

@JvmInline
value class MatchScoreId(val value: String)


class MatchScore private constructor(
    val id: MatchScoreId,
    val matchId: MatchId,
    val playerId: PlayerId,
    val teamId: TeamId,
    val value: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchScore

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: MatchScoreId,
            matchId: MatchId,
            playerId: PlayerId,
            teamId: TeamId,
            value: Int,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): MatchScore? {
            return MatchScore(id, matchId, playerId,teamId, value, created, updated, deleted)
        }
    }
}