package com.hfu.bierolympiade.data.database.event

import com.hfu.bierolympiade.data.database.Converters.timestampFromDb
import com.hfu.bierolympiade.data.database.game.gameFromDb
import com.hfu.bierolympiade.data.database.match.matchFromDb
import com.hfu.bierolympiade.data.database.player.playerFromDb
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId


fun eventToDb(event: Event): EventDb = EventDb(
    id = event.id.value,
    name = event.name,
    location = event.location,
    date = event.date,
    fees = event.fees,
    created = event.created,
    updated = event.updated,
    deleted = event.deleted,
)

fun eventFromDb(eventWithMatches: EventWithEverything): Event? {
    return Event.create(
        id = EventId(eventWithMatches.event.id),
        name = eventWithMatches.event.name,
        location = eventWithMatches.event.location,
        date = eventWithMatches.event.date,
        fees = eventWithMatches.event.fees,
        created = eventWithMatches.event.created,
        updated = eventWithMatches.event.updated,
        deleted = eventWithMatches.event.deleted,
        matches = eventWithMatches.matches.mapNotNull { matchFromDb(it) },
        games = eventWithMatches.games.mapNotNull { gameFromDb(it) },
        players = eventWithMatches.players.mapNotNull { playerFromDb(it) }
    )
}
