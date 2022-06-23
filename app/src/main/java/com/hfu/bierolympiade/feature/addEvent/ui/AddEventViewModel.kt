package com.hfu.bierolympiade.feature.addEvent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.domain.*
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.MatchId
import com.hfu.bierolympiade.domain.model.PlayerId
import com.hfu.bierolympiade.domain.model.TeamId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val deleteEventById: DeleteEventByIdUseCase
) : ViewModel() {

    var eventId: String? = ""

    init {
        viewModelScope.launch { initialAddEvent("", "", "", 0) }
    }

    suspend fun initialAddEvent(
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
            val event = getEventById(eventId) ?: return@launch

            val players = event.players.shuffled()
            getGamesFromEvent(event).mapNotNull { game ->
                val teamCount = players.size / game!!.teamSize
                var teams: List<String> = emptyList()
                for (i in 0..teamCount) {
                    val team = addTeam()
                    if (team != null)
                        teams = teams.plus(team)
                }
                val matchCount = teamCount / 2
                var matches: List<String> = emptyList()
                for (i in 0..matchCount) {
                    val match = addMatch(eventId, game.id)
                    if (match != null)
                        matches = matches.plus(match)
                }
                var count = 0
                players.map {
                    if (count == game.teamSize)
                        count = 0
                    addMatchParticipant(
                        MatchId(matches[count / 2]),
                        PlayerId(it),
                        TeamId(teams[count])
                    )
                    count++
                }
            }
            updateEvent(eventId, name, location, date, fees, false)
        }
    }

    /*TODO onSaveEvent -> Update Event */
    fun onDiscard(){
        val safeId = eventId ?: "";
        viewModelScope.launch{
            if(safeId != "")
                deleteEventById(EventId(safeId))
        }
    }
}