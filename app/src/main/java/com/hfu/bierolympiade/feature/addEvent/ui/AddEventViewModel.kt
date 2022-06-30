package com.hfu.bierolympiade.feature.addEvent.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.hfu.bierolympiade.data.database.event.EventWithMatchesAndGamesAndPlayers
import com.hfu.bierolympiade.domain.*
import com.hfu.bierolympiade.domain.model.*
import com.hfu.bierolympiade.feature.addplayertoevent.ui.AddPlayerToEventUI
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
    private val addMatchScore: AddMatchScoreUseCase
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


    fun onSaveEvent(eventId: EventId, name: String, location: String, date: String, fees: Int) {
        viewModelScope.launch {
            Timber.log(Log.INFO, eventId.value)
            val event = getEventById(eventId) ?: return@launch

            val players = event.players.shuffled()
            getGamesFromEvent(event).mapNotNull { game ->
                val teamCount: Int = roundLowerEven((players.size.toDouble() / (game?.teamSize ?: 1.0).toDouble()))
                Timber.log(Log.INFO, teamCount.toString())
                var teams: List<String> = emptyList()
                for (i in 0 until teamCount) {
                    val team = addTeam()
                    if (team != null)
                        teams = teams.plus(team)
                }
                val matchCount = teamCount / 2
                var matches: List<String> = emptyList()
                for (i in 0 until matchCount) {
                    val match = game?.let { addMatch(eventId, it.id) }
                    if (match != null)
                        matches = matches.plus(match)
                }
                var count = 0
                players.map {
                    if (game != null) {
                        if (count == game.teamSize && game.teamSize != 1 || count == teamCount)
                            count = 0
                    }
                    addMatchParticipant(
                        MatchId(matches[count / 2]),
                        PlayerId(it),
                        TeamId(teams[count])
                    )
                    addMatchScore(
                        MatchId(matches[count / 2]),
                        TeamId(teams[count]),
                        PlayerId(it),
                        0
                    )
                    count++
                }
            }
            updateEvent(eventId, name, location, date, fees, false)
        }
    }
    fun onDiscard(){
        val safeId = eventId ?: "";
        viewModelScope.launch{
            if(safeId != "")
                deleteEventById(EventId(safeId))
        }
    }

    private fun roundLowerEven(value: Double): Int = (kotlin.math.floor(value / 2) * 2).toInt()
<<<<<<< Updated upstream

    suspend fun getEvent(eventId: String): Event?{
        return getEventById(EventId(eventId))
    }

=======
>>>>>>> Stashed changes
}