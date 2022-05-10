package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.leaderboardRepo
import com.hfu.bierolympiade.domain.model.EventId

class GetLeaderboardFromEventUseCase {
    operator fun invoke(eventId: EventId) = leaderboardRepo.getLeaderboardByEvent(eventId)
}