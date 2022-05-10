package com.hfu.bierolympiade.feature.leaderboard.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfu.bierolympiade.domain.GetLeaderboardFromEventUseCase
import com.hfu.bierolympiade.domain.GetPlayerByIdUseCase
import com.hfu.bierolympiade.domain.model.EventId


class LeaderboardViewModel : ViewModel() {
    fun bindUi(context: Context): LiveData<List<LeaderboardUI>> {
        val state = MutableLiveData(
            GetLeaderboardFromEventUseCase()(eventId = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"))?.standings?.map{
                LeaderboardUI(
                    playerName = GetPlayerByIdUseCase()(it.key)?.name ?: "unknown",
                    points = it.value
                )
            }?.sortedBy { it.points }?: emptyList<LeaderboardUI>()
        )
        return state
    }
}