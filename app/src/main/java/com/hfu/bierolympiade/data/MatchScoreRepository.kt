package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.matchScore.MatchScoreDao
import com.hfu.bierolympiade.data.database.matchScore.matchScoreFromDb
import com.hfu.bierolympiade.data.database.matchScore.matchScoreToDb
import com.hfu.bierolympiade.domain.model.MatchScore
import com.hfu.bierolympiade.domain.model.MatchScoreId
import javax.inject.Inject

class MatchScoreRepository @Inject constructor(
    private val dao: MatchScoreDao
) {

    suspend fun getAllMatchScores(): List<MatchScore> = dao.getAll().mapNotNull { matchScoreFromDb(it) }

    suspend fun getMatchScoreById(id: MatchScoreId): MatchScore? = dao.getById(id.value)?.let { matchScoreFromDb(it) }

    suspend fun addMatchScore(matchScore: MatchScore) {
        dao.insert(matchScoreToDb(matchScore))
    }

    suspend fun updateMatchScore(newMatchScore: MatchScore) {
        dao.update(matchScoreToDb(newMatchScore))
    }
}