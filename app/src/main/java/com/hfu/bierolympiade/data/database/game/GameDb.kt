package com.hfu.bierolympiade.data.database.game

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.hfu.bierolympiade.data.database.gameType.GameTypeDb
import com.hfu.bierolympiade.data.database.match.MatchDb
import java.time.ZonedDateTime


@Entity(tableName = "game")
data class GameDb(
    @PrimaryKey
    val gameId: String,
    val eventId: String,
    val gameTypeId: String,
    val status: String,
    val rules: String,
    val teamSize: Int,
    val winCondition: Int,
    val points: Int,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class GameWithMatches(
    @Embedded val game: GameDb,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "gameId"
    )
    val matches: List<MatchDb>
)

data class GameWithMatchesAndType(
    @Embedded val game: GameWithMatches,
    @Relation(
        entity = GameTypeDb::class,
        parentColumn = "gameTypeId",
        entityColumn = "gameTypeId"
    )
    val gameType: GameTypeDb
)