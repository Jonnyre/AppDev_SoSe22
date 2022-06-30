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
    private val getMatchesFromGame: GetMatchesFromGameUseCase,
    private val getPlayerById: GetPlayerByIdUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<LeaderboardUI>> = liveData {
        val result = getEventsWithoutTemporary().map {
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
            val event = getEventById(eventId) ?: return@launch

            val players = getPlayersForEvent(event)
            val leaderBoard = players.associate { (it?.id) to 0 }.toMutableMap()
            getGamesFromEvent(event).mapNotNull { game ->
                if (game != null) {
                    val matches = getMatchesFromGame(game)
                    matches.map { match ->
                        val matchScores =
                            match?.matchScores?.mapNotNull {
                                getMatchScoreById(MatchScoreId(it))
                            }
                        val winnerMatchScores =
                            matchScores?.filter { score -> score.value == game.winCondition }
                        winnerMatchScores?.map { winnerMatchScore ->
                            leaderBoard.set(
                                winnerMatchScore.playerId,
                                leaderBoard[winnerMatchScore.playerId]?.plus(game.points)
                                    ?: 0
                            )
                        }
                    }
                }
            }
            leaderBoardItems.value = leaderBoard.mapNotNull { it1 ->
                it1.key?.let { getPlayerById(it)?.name ?: "" }?.let {
                    LeaderBoardItemUI(it, it1.value)
                }
            }.sortedByDescending { it.points }
        }
    }
}