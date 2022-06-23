package com.hfu.bierolympiade.domain.model

import java.time.ZonedDateTime

@JvmInline
value class TeamId(val value: String)


class Team private constructor(
    val id: TeamId,
    val matchScores: List<String>,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: TeamId,
            matchScores: List<String>,
            created: ZonedDateTime = ZonedDateTime.now(),
            updated: ZonedDateTime = ZonedDateTime.now(),
            deleted: ZonedDateTime = ZonedDateTime.now(),
        ): Team? {
            return Team(id, matchScores, created, updated, deleted)
        }
    }
}