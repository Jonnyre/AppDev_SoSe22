package com.hfu.bierolympiade.feature.pickGameType.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.data.GameTypeRepository
import com.hfu.bierolympiade.domain.AddEventUseCase
import com.hfu.bierolympiade.domain.GetAllGameTypesUseCase
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.GameTypeId
import com.hfu.bierolympiade.feature.leaderboard.ui.LeaderboardUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class pickGameTypeViewModel @Inject constructor(
    private val GetAllGameTypes: GetAllGameTypesUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<pickGameTypeUI>> = liveData {
    val result = GetAllGameTypes()
            .map {
                pickGameTypeUI(
                    id = it.gameTypeId,
                    name = it.name,
                    icon = it.icon,
                )
            }
        emit(result)


        /*?.standings?.map { board ->
        LeaderboardUI(
            playerName = GetPlayerById(board.key)?.name ?: "unknown",
            points = board.value
        )
    }?.sortedByDescending { it.points } ?: emptyList<LeaderboardUI>()
emit(result)*/
    }
}