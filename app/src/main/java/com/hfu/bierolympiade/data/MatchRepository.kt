package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.match.MatchDao
import com.hfu.bierolympiade.data.database.match.matchFromDb
import com.hfu.bierolympiade.data.database.match.matchToDb
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.Match
import com.hfu.bierolympiade.domain.model.MatchId
import java.time.ZonedDateTime
import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val dao: MatchDao
) {

    private val allMatchs = listOf(
        Match.create(
            id = MatchId("dc86a2f3-7b0a-4925-8f91-c62bbf2e026f"),
            eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
            gameId = GameId("a59c0e7b-3a58-4859-934d-1b0393831637"),
            date = ZonedDateTime.now(),
            state = 0,
            type = 0,
        ),
        Match.create(
            id = MatchId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"),
            eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
            gameId = GameId("a59c0e7b-3a58-4859-934d-1b0393831637"),
            date = ZonedDateTime.now(),
            state = 0,
            type = 0,
        ),
    ).filterNotNull()

    suspend fun getAllMatches(): List<Match> = dao.getAll().mapNotNull { matchFromDb(it) }

    suspend fun getMatchById(id: MatchId): Match? = dao.getById(id.value)?.let { matchFromDb(it) }

    suspend fun addMatch(Match: Match) {
        dao.insert(matchToDb(Match))
    }
}