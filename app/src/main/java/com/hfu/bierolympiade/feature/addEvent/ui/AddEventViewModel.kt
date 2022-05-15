package com.hfu.bierolympiade.feature.addEvent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.domain.AddEventUseCase
import kotlinx.coroutines.launch

class AddEventViewModel : ViewModel() {
    fun onAddEvent(name: String, location: String, date: String, fees: Int) {
        viewModelScope.launch {
            AddEventUseCase()(name, location, date, fees)
        }
    }
}