package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.data.database.matchparticipant.MatchParticipantDao
import com.hfu.bierolympiade.data.database.matchparticipant.matchParticipantFromDb
import com.hfu.bierolympiade.data.database.matchparticipant.matchParticipantToDb
import com.hfu.bierolympiade.domain.model.MatchParticipant
import com.hfu.bierolympiade.domain.model.MatchParticipantId
import javax.inject.Inject


class MatchParticipantRepository @Inject constructor(
    private val dao: MatchParticipantDao
) {

    suspend fun getAllMatchParticipantes(): List<MatchParticipant> = dao.getAll().mapNotNull { matchParticipantFromDb(it) }

    suspend fun getMatchParticipantById(id: MatchParticipantId): MatchParticipant? = dao.getById(id.value)?.let { matchParticipantFromDb(it) }

    suspend fun addMatchParticipant(MatchParticipant: MatchParticipant) {
        dao.insert(matchParticipantToDb(MatchParticipant))
    }
}