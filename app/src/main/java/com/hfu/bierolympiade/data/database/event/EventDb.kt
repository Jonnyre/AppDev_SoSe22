package com.hfu.bierolympiade.data.database.event

import androidx.room.*
import com.hfu.bierolympiade.data.database.game.GameDb
import com.hfu.bierolympiade.data.database.game.GameWithMatches
import com.hfu.bierolympiade.data.database.match.MatchDb
import com.hfu.bierolympiade.data.database.player.PlayerDb
import com.hfu.bierolympiade.data.database.player.PlayerWithEvent
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb
import com.hfu.bierolympiade.domain.model.Player
import java.time.ZonedDateTime


@Entity(tableName = "event")
data class EventDb(
    @PrimaryKey
    val eventId: String,
    val name: String,
    val location: String,
    val date: String,
    val fees: Int?,
    val isTemporary: Boolean,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)

data class EventWithMatches(
    @Embedded val event: EventDb,
    @Relation(
        entity = MatchDb::class,
        parentColumn = "eventId",
        entityColumn = "eventId",
    )
    val matches: List<MatchDb>,
)

data class EventWithMatchesAndGames(
    @Embedded val event: EventWithMatches,
    @Relation(
        entity = GameDb::class,
        parentColumn = "eventId",
        entityColumn = "eventId",
    )
    val games: List<GameWithMatches>,
)
data class EventWithMatchesAndGamesAndPlayers(
    @Embedded val event: EventWithMatchesAndGames,
    @Relation(
        parentColumn = "eventId",
        entityColumn = "playerId",
        associateBy = Junction(PlayerEventCrossRefDb::class)
    )
    val players: List<PlayerDb>
)