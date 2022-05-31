package com.hfu.bierolympiade.data.database.game

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.hfu.bierolympiade.data.database.match.MatchDb
import java.time.ZonedDateTime


@Entity(tableName = "game")
data class GameDb(
    @PrimaryKey
    val id: String,
    val eventId: String,
    val name: String,
    val status: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class GameWithMatches(
    @Embedded val game: GameDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    )
    val matches: List<MatchDb>,
)