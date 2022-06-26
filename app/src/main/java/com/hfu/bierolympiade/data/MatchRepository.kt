package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.match.MatchDao
import com.hfu.bierolympiade.data.database.match.matchFromDb
import com.hfu.bierolympiade.data.database.match.matchToDb
import com.hfu.bierolympiade.domain.model.Match
import com.hfu.bierolympiade.domain.model.MatchId
import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val dao: MatchDao
) {

    suspend fun getAllMatches(): List<Match> = dao.getAll().mapNotNull { matchFromDb(it) }

    suspend fun getMatchById(id: MatchId): Match? = dao.getById(id.value)?.let { matchFromDb(it) }

    suspend fun addMatch(Match: Match) {
        dao.insert(matchToDb(Match))
    }
}