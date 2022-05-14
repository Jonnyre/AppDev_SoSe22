package com.hfu.bierolympiade.domain.model


class Leaderboard private constructor(
    val event: EventId,
    val standings: Map<PlayerId, Int>,
) {
    companion object {
       fun create(
           event: EventId,
           standings: Map<PlayerId, Int>
       ): Leaderboard? {
           if(standings.isEmpty() || event.value == ""){
               return null
           }
           return Leaderboard(event, standings)
       }
    }
}