package com.hfu.bierolympiade.data.database.team

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.hfu.bierolympiade.data.database.match.MatchDb
import com.hfu.bierolympiade.data.database.matchparticipant.MatchParticipantDb
import java.time.ZonedDateTime

@Entity(tableName = "team")
data class TeamDb(
    @PrimaryKey
    val teamId: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class TeamWithMatchParticipant(
    @Embedded val teamDb: TeamDb,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "teamId"
    )
    val matchParticipants: List<MatchParticipantDb>
)