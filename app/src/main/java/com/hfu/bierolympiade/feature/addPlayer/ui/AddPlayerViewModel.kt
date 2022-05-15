package com.hfu.bierolympiade.feature.addPlayer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.domain.AddPlayerUseCase
import kotlinx.coroutines.launch

class AddPlayerViewModel : ViewModel() {
    fun onAddPlayer(name: String, music: String, description: String) {
        viewModelScope.launch {
            AddPlayerUseCase()(name, music, description)
        }
    }
}