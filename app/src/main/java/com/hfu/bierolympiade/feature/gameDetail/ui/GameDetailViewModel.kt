package com.hfu.bierolympiade.feature.gameDetail.ui
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetGameByIdUseCase
import com.hfu.bierolympiade.domain.GetMatchesFromGameUseCase
import com.hfu.bierolympiade.domain.GetPlayerFromMatchParticipantUseCase
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.MatchParticipantId
import com.hfu.bierolympiade.feature.eventDetail.ui.GameUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val GetGameById: GetGameByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val GetMatchesFromGame: GetMatchesFromGameUseCase,
    private val getPlayerFromMatchParticipant: GetPlayerFromMatchParticipantUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<MatchUI>> = liveData {
        val id = savedStateHandle.get<String>("id").orEmpty()
        val game = GetGameById(GameId(id)) ?: return@liveData
        val result = GetMatchesFromGame(game).map { match ->
            match?.let {
                val players = match.matchParticipant.map { participantId ->
                    getPlayerFromMatchParticipant(MatchParticipantId(participantId))
                }

                val teamId = players.distinctBy { player -> player?.teamId }
                val team1 = players.filter{player -> player?.teamId == teamId[0]?.teamId}
                val team2 = players.filter{player -> player?.teamId == teamId[1]?.teamId}
                MatchUI(
                    id = it.id,
                    state = match.state,
                    type = match.type,
                    playerNamesTeam1 = team1.mapNotNull { player ->
                        player?.player?.name
                    },
                    playerNamesTeam2 = team2.mapNotNull { player ->
                        player?.player?.name
                    }
                )
            }
        }
        emit(result as List<MatchUI>)
    }
}