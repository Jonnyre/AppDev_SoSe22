package com.hfu.bierolympiade.feature.gameDetail.ui
import android.content.Context
import androidx.lifecycle.*
import com.hfu.bierolympiade.domain.*
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.MatchParticipantId
import com.hfu.bierolympiade.domain.model.TeamId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameById: GetGameByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getMatchesFromGame: GetMatchesFromGameUseCase,
    private val getPlayerFromMatchParticipant: GetPlayerFromMatchParticipantUseCase,
    private val updateMatchScore: UpdateMatchScoreUseCase,
    private val getMatchScoreFromTeam: GetMatchScoreFromTeamUseCase,
    private val getTeamById: GetTeamByIdUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<MatchUI>> = liveData {
        val id = savedStateHandle.get<String>("id").orEmpty()
        val game = getGameById(GameId(id)) ?: return@liveData
        val result = getMatchesFromGame(game).map { match ->
            match?.let {
                val players = match.matchParticipant.map { participantId ->
                    getPlayerFromMatchParticipant(MatchParticipantId(participantId))
                }

                val teamId = players.distinctBy { player -> player?.teamId }
                val playerWithTeam1 = players.filter{player -> player?.teamId == teamId[0]?.teamId}
                val playerWithTeam2 = players.filter{player -> player?.teamId == teamId[1]?.teamId}

                teamId[0]?.teamId?.let { teamId1 ->
                    teamId[1]?.teamId?.let { teamId2 ->
                        val team1 = getTeamById(teamId1)
                        val team2 = getTeamById(teamId2)
                        if (team1 != null && team2 != null) {
                            val matchScore1 = getMatchScoreFromTeam(team1)
                            val matchScore2 = getMatchScoreFromTeam(team2)
                            matchScore1[0]?.value?.let { matchScoreValue1 ->
                                matchScore2[0]?.value?.let { matchScoreValue2 ->
                                    return@map MatchUI(
                                        id = it.id,
                                        state = match.state,
                                        type = match.type,
                                        winCondition = game.winCondition,
                                        playerNamesTeam1 = playerWithTeam1.mapNotNull { player ->
                                            player?.player?.name
                                        },
                                        playerNamesTeam2 = playerWithTeam2.mapNotNull { player ->
                                            player?.player?.name
                                        },
                                        team1 = teamId1,
                                        team2 = teamId2,
                                        score1 = matchScoreValue1,
                                        score2 = matchScoreValue2
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        emit(result as List<MatchUI>)
    }

    fun updateMatchScoreValue(teamId: TeamId, value: Int) {
        viewModelScope.launch {
            val team = getTeamById(teamId)
            if (team != null) {
                val matchScores = getMatchScoreFromTeam(team)
                matchScores.mapNotNull {
                    if (it != null) {
                        updateMatchScore(it, value)
                    }
                }
            }
        }
    }
}