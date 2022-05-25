package com.hfu.bierolympiade.feature.addEvent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.domain.AddEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEvent: AddEventUseCase
)   : ViewModel() {
    fun onAddEvent(name: String, location: String, date: String, fees: Int) {
        viewModelScope.launch {
            addEvent(name, location, date, fees)
        }
    }
}