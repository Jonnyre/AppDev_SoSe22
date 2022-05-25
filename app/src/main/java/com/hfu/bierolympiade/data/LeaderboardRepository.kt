package com.hfu.bierolympiade.data

import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Leaderboard
import com.hfu.bierolympiade.domain.model.PlayerId
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(

) {

    private val allLeaderboards = listOfNotNull(
        Leaderboard.create(
            event = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
            standings = mapOf(
                Pair(PlayerId("a59c0e7b-3a58-4859-934d-1a0393831637"), 12),
                Pair(PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"), 0),
                Pair(PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"), 2),
            )
        ),
        Leaderboard.create(
            event = EventId("867e5af2-aa53-4e46-9cfd-a1bc9b8929cb"),
            standings = mapOf(
                Pair(PlayerId("a59c0e7b-3a58-4859-934d-1a0393831637"), 3),
                Pair(PlayerId("867e5af2-aa53-4e46-9cfd-a1bc9b2929cb"), 5),
                Pair(PlayerId("f16cdf15-6528-4a0b-993c-24d5bf8007a7"), 22),
            )
        )
    )

    fun allLeaderboards() = allLeaderboards


    fun getLeaderboardByEvent(id: EventId): Leaderboard? = allLeaderboards.firstOrNull {
        it.event == id
    }
}
