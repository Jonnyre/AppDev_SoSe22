package com.hfu.bierolympiade.feature.addPlayer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfu.bierolympiade.domain.AddPlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlayerViewModel @Inject constructor(
    private val AddPlayer: AddPlayerUseCase
) : ViewModel() {
    fun onAddPlayer(name: String, music: String, description: String) {
        viewModelScope.launch {
            AddPlayer(name, music, description)
        }
    }
}