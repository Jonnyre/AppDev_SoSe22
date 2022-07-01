package com.hfu.bierolympiade.feature.addGame.ui

import android.content.Context
import androidx.lifecycle.*
import com.hfu.bierolympiade.domain.AddGameUseCase
import com.hfu.bierolympiade.domain.GetGameByIdUseCase
import com.hfu.bierolympiade.domain.GetGameTypeByIdUseCase
import com.hfu.bierolympiade.domain.UpdateGameUseCase
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameId
import com.hfu.bierolympiade.domain.model.GameTypeId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGameViewModel @Inject constructor(
    val getGameTypeById: GetGameTypeByIdUseCase,
    val addGame: AddGameUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getGameById: GetGameByIdUseCase,
    private val updateGame: UpdateGameUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<AddGameUI> = liveData {
        val result: AddGameUI?
        val gameId = savedStateHandle["gameId"] ?: ""
        val game = getGameById(GameId(gameId))
        if (game != null) {
            result = getGameTypeById(game.gameTypeId).let { gameType ->
                gameType?.let {
                    AddGameUI(
                        game.id,
                        game.eventId,
                        game.rules,
                        game.teamSize,
                        game.winCondition,
                        game.points,
                        gameType.isHighScore
                    )
                }
            }
        } else {
            result =
                getGameTypeById(GameTypeId(savedStateHandle["gameTypeId"] ?: "")).let { gameType ->
                    gameType?.let {
                        AddGameUI(
                            null,
                            EventId(savedStateHandle["eventId"] ?: ""),
                            it.rules,
                            0,
                            0,
                            0,
                            it.isHighScore
                        )
                    }
                }
        }
        if (result != null) {
            emit(result)
        }
    }

    fun onSaveGame(teamSize: Int, winCondition: Int, points: Int, rules: String) {
        val eventId = EventId(savedStateHandle.get("eventId") ?: "")
        viewModelScope.launch {
            addGame(
                status = "Not yet started",
                points = points,
                eventId = eventId,
                winCondition = winCondition,
                teamSize = teamSize,
                rules = rules,
                gameTypeId = GameTypeId(savedStateHandle.get("gameTypeId") ?: "")
            )
        }
    }

    fun onUpdateGame(teamSize: Int, winCondition: Int, points: Int, rules: String) {
        val gameId = savedStateHandle["gameId"] ?: ""
        viewModelScope.launch {
            val game = getGameById(GameId(gameId))
            if (game != null) {
                updateGame(
                    game.id,
                    game.name,
                    game.icon,
                    rules,
                    game.status,
                    game.matches,
                    game.eventId,
                    game.gameTypeId,
                    teamSize,
                    winCondition,
                    points,
                    game.created,
                    game.deleted
                )
            }
        }
    }
}