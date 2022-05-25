package com.hfu.bierolympiade.feature.leaderboard.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hfu.bierolympiade.domain.GetLeaderboardFromEventUseCase
import com.hfu.bierolympiade.domain.GetPlayerByIdUseCase
import com.hfu.bierolympiade.domain.model.EventId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val GetLeaderboardFromEvent: GetLeaderboardFromEventUseCase,
    private val GetPlayerById: GetPlayerByIdUseCase
): ViewModel() {
    fun bindUi(context: Context): LiveData<List<LeaderboardUI>> = liveData {
        val result =
            GetLeaderboardFromEvent(eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"))?.standings?.map { board ->
                LeaderboardUI(
                    playerName = GetPlayerById(board.key)?.name ?: "unknown",
                    points = board.value
                )
            }?.sortedBy { it.points } ?: emptyList<LeaderboardUI>()
        emit(result)
    }
}