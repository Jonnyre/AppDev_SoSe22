package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.LeaderboardRepository
import com.hfu.bierolympiade.domain.model.EventId
import javax.inject.Inject

class GetLeaderboardFromEventUseCase @Inject constructor(
    private val leaderboardRepository: LeaderboardRepository
) {
    operator fun invoke(eventId: EventId) = leaderboardRepository.getLeaderboardByEvent(eventId)
}