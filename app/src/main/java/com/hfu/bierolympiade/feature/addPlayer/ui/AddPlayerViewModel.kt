package com.hfu.bierolympiade.feature.addPlayer.ui

import android.content.Context
import androidx.lifecycle.*
import com.hfu.bierolympiade.domain.*
import com.hfu.bierolympiade.domain.model.MatchParticipantId
import com.hfu.bierolympiade.domain.model.MatchScoreId
import com.hfu.bierolympiade.domain.model.PlayerId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlayerViewModel @Inject constructor(
    private val AddPlayer: AddPlayerUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val GetPlayerById: GetPlayerByIdUseCase,
    private val UpdatePlayer: UpdatePlayerUseCase,
    private val getMatchFromMatchParticipant: GetMatchFromMatchParticipantUseCase,
    private val getMatchScoreById: GetMatchScoreByIdUseCase,
    private val getGameById: GetGameByIdUseCase
) : ViewModel() {

    fun bindUi(context: Context): LiveData<AddPlayerUI?> = liveData {
        val playerId = savedStateHandle.get<String>("playerId")
        val result = when (playerId == null) {
            true -> AddPlayerUI(
                id = null,
                name = "",
                description = "",
                music = "",
                winningMatches = 0,
                losingMatches = 0
            )
            false -> GetPlayerById(PlayerId(playerId))?.let { player ->
                val matches = player.matchParticipants.mapNotNull { matchParticipantId ->
                    getMatchFromMatchParticipant(MatchParticipantId(matchParticipantId))
                }
                var winningMatches = 0
                var losingMatches = 0
                matches.map { match ->
                    val matchScores =
                        match.matchScores.mapNotNull { getMatchScoreById(MatchScoreId(it)) }
                    val game = getGameById(match.gameId)
                    val winnerMatchScores =
                        matchScores.filter { score -> score.value == game?.winCondition }
                    val isWinner = winnerMatchScores?.find { matchScore ->
                        matchScore.playerId == player.id
                    }
                    if (isWinner != null) winningMatches += 1
                    else losingMatches += 1
                }
                AddPlayerUI(
                    player.id,
                    player.name,
                    "",
                    player.description,
                    winningMatches,
                    losingMatches
                )
            }
        }
        emit(result)
    }

    fun onSavePlayer(name: String, music: String, description: String) {
        viewModelScope.launch {
            val playerId = savedStateHandle.get<String>("playerId")
            if (playerId == null)
                AddPlayer(name, music, description)
            else
                UpdatePlayer(PlayerId(playerId), name, description)
        }
    }
}