package com.hfu.bierolympiade.feature.addEvent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.domain.AddEventUseCase
import kotlinx.coroutines.launch

class AddEventViewModel : ViewModel() {
    fun onAddEvent(addEventUI: AddEventUI) {
        viewModelScope.launch {
            AddEventUseCase()(addEventUI)
        }
    }
}