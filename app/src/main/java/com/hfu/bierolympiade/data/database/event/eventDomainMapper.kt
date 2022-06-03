package com.hfu.bierolympiade.data.database.event

import com.hfu.bierolympiade.data.database.Converters.timestampFromDb
import com.hfu.bierolympiade.data.database.game.gameFromDb
import com.hfu.bierolympiade.data.database.match.matchFromDb
import com.hfu.bierolympiade.data.database.player.playerFromDb
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId


fun eventToDb(event: Event): EventDb = EventDb(
    eventId = event.id.value,
    name = event.name,
    location = event.location,
    date = event.date,
    fees = event.fees,
    created = event.created,
    updated = event.updated,
    deleted = event.deleted,
)

fun eventFromDb(eventWithMatches: EventWithMatchesAndGamesAndPlayers): Event? {
    return Event.create(
        id = EventId(eventWithMatches.event.event.event.eventId),
        name = eventWithMatches.event.event.event.name,
        location = eventWithMatches.event.event.event.location,
        date = eventWithMatches.event.event.event.date,
        fees = eventWithMatches.event.event.event.fees,
        created = eventWithMatches.event.event.event.created,
        updated = eventWithMatches.event.event.event.updated,
        deleted = eventWithMatches.event.event.event.deleted,
        matches = eventWithMatches.event.event.matches.mapNotNull { it.matchId },
        games = eventWithMatches.event.games.mapNotNull { it.game.gameId },
        players = eventWithMatches.players.mapNotNull { it.playerId }
    )
}
