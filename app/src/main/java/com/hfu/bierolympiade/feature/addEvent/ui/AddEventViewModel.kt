package com.hfu.bierolympiade.feature.addEvent.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.domain.AddEventUseCase
import com.hfu.bierolympiade.feature.player.ui.PlayerUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEvent: AddEventUseCase
)   : ViewModel() {

    fun initialAddEvent(context: Context, name: String, location: String, date: String, fees: Int): LiveData<String?> = liveData {
        emit(null)
        val result = addEvent(name, location, date, fees, 1)
        emit(result)
    }

    fun onSaveEvent() {
        /*TODO onSaveEvent -> Update Event */
    }
}