package com.hfu.bierolympiade.feature.addEvent.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.hfu.bierolympiade.domain.*
import com.hfu.bierolympiade.domain.model.*
import com.hfu.bierolympiade.feature.main.ui.navControllerGlobal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEvent: AddEventUseCase,
    private val getGamesFromEvent: GetGamesFromEventUseCase,
    private val getEventById: GetEventByIdUseCase,
    private val addTeam: AddTeamUseCase,
    private val addMatch: AddMatchUseCase,
    private val addMatchParticipant: AddMatchParticipantUseCase,
    private val updateEvent: UpdateEventUseCase,
    private val deleteEventById: DeleteEventByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val addMatchScore: AddMatchScoreUseCase,
    private val deleteGameById: DeleteGameByIdUseCase,
    private val getGameTypeById: GetGameTypeByIdUseCase,
) : ViewModel() {

    var eventId: String? = savedStateHandle.get<String>("eventId")

    init {
        val eventId = savedStateHandle.get<String>("eventId")
        if (eventId == null){
            viewModelScope.launch { initialAddEvent("", "", "", 0) }
        }
    }

    fun bindUi(context: Context): LiveData<Event?> = liveData {
        val result = getEventById(EventId(eventId ?: ""))
        emit(result)
    }

    fun bindUiGames(context: Context): LiveData<List<Game>> = liveData {
        val safeEventId = eventId?: ""
        val event = getEvent(safeEventId)
        if(event != null){
            val result = getGamesFromEvent(event)
            emit(result)
        }
    }

    private suspend fun initialAddEvent(
        name: String,
        location: String,
        date: String,
        fees: Int
    ) {
        eventId = withContext(viewModelScope.coroutineContext) {
            addEvent(name, location, date, fees, true)
        }
    }

    fun saveEvent(eventId: EventId, name: String, location: String, date: String, fees: Int, isTemporary: Boolean, route: String){
        viewModelScope.launch {
            updateEvent(eventId, name, location, date, fees, isTemporary)
            navControllerGlobal?.navigate(route)
        }
    }

    fun onSaveEvent(eventId: EventId, name: String, location: String, date: String, fees: Int) {
        viewModelScope.launch {
            val event = getEventById(eventId) ?: return@launch

            getGamesFromEvent(event).map { game ->
                val players = event.players.shuffled()
                if (game != null) {
                    if (getGameTypeById(game.gameTypeId)?.isHighScore == true) {
                        players.map {
                            addTeam()?.let { it1 -> TeamId(it1) }?.let { it2 ->
                                addMatch(eventId, game.id)?.let { it1 -> MatchId(it1) }
                                    ?.let { it3 ->
                                        addMatchParticipant(it3, PlayerId(it), it2)
                                        addMatchScore(it3, it2, PlayerId(it), 0)
                                    }
                            }
                        }
                    } else {
                        val teamCount: Int =
                            roundLowerEven((players.size.toDouble() / game.teamSize.toDouble()))
                        Timber.log(Log.INFO, "Teams: $teamCount")
                        var teams: List<String> = emptyList()
                        for (i in 0 until teamCount) {
                            val team = addTeam()
                            if (team != null)
                                teams = teams.plus(team)
                        }
                        val matchCount = teamCount / 2
                        var matches: List<String> = emptyList()
                        for (i in 0 until matchCount) {
                            val match = addMatch(eventId, game.id)
                            if (match != null)
                                matches = matches.plus(match)
                        }
                        var teamCounter = 0
                        var matchCounter = 0
                        players.map {
                            if (teamCounter == teamCount)
                                teamCounter = 0

                            if(teamCounter % 2 == 0)
                                matchCounter++

                            if(matchCounter == matchCount)
                                matchCounter = 0

                            addMatchParticipant(
                                MatchId(matches[matchCounter]),
                                PlayerId(it),
                                TeamId(teams[teamCounter])
                            )
                            addMatchScore(
                                MatchId(matches[matchCounter]),
                                TeamId(teams[teamCounter]),
                                PlayerId(it),
                                0
                            )
                            teamCounter++
                        }
                    }
                }
            }
            Timber.log(Log.INFO, "EventUpdate" + eventId.value)
            updateEvent(eventId, name, location, date, fees, false)
            navControllerGlobal?.popBackStack()
            navControllerGlobal?.navigate("events")
        }
    }

    fun onDiscard(){
        val safeId = eventId ?: ""
        viewModelScope.launch{
            if(safeId != "")
                deleteEventById(EventId(safeId))
        }
    }

    private fun roundLowerEven(value: Double): Int = (kotlin.math.floor(value / 2) * 2).toInt()

    private suspend fun getEvent(eventId: String): Event?{
        return getEventById(EventId(eventId))
    }

    fun deleteGame(gameId: GameId){
        viewModelScope.launch {
            deleteGameById(gameId)
        }
    }



}