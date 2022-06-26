package com.hfu.bierolympiade.feature.leaderboard.ui

import android.content.Context
import androidx.lifecycle.*
import com.hfu.bierolympiade.domain.*
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.MatchScoreId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val getEventsWithoutTemporary: GetEventsWithoutTemporaryUseCase,
    private val getPlayersForEvent: GetPlayersForEventUseCase,
    private val getEventById: GetEventByIdUseCase,
    private val getMatchScoreById: GetMatchScoreByIdUseCase,
    private val getGamesFromEvent: GetGamesFromEventUseCase,
    private val getMatchesFromGame: GetMatchesFromGameUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<LeaderboardUI>> = liveData {
        val result = getEventsWithoutTemporary().mapNotNull {
            LeaderboardUI(
                eventId = it.id,
                name = it.name
            )
        }.sortedBy { it.name }
        emit(result)
    }

    val leaderBoardItems = MutableLiveData<List<LeaderBoardItemUI>>()

    fun getLeaderBoardForEvent(eventId: EventId) {
        viewModelScope.launch {
            var points = 0
            val event = getEventById(eventId)
            if (event != null) {
                leaderBoardItems.value =
                getPlayersForEvent(event).mapNotNull { player ->
                    points = 0
                    getGamesFromEvent(event).mapNotNull { game ->
                        if (game != null) {
                            val matches = getMatchesFromGame(game)
                            matches.map { match ->
                                val matchScores =
                                    match?.matchScores?.mapNotNull { getMatchScoreById(MatchScoreId(it)) }
                                val winnerMatchScores =
                                    matchScores?.filter { score -> score.value == game.winCondition }
                                winnerMatchScores?.map {
                                    if (player != null) {
                                        if (it.playerId == player.id)
                                            points += game.points
                                    }
                                } ?: ""
                            }
                        }
                    }
                    player?.let { it1 -> LeaderBoardItemUI(it1.name, points) }
                }.sortedByDescending { it.points }
            }
        }
    }
}