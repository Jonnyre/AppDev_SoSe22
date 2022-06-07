package com.hfu.bierolympiade.feature.addGame.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.hfu.bierolympiade.data.GameTypeRepository
import com.hfu.bierolympiade.domain.AddGameUseCase
import com.hfu.bierolympiade.domain.AddPlayerUseCase
import com.hfu.bierolympiade.domain.GetGameTypeByIdUseCase
import com.hfu.bierolympiade.domain.model.GameType
import com.hfu.bierolympiade.domain.model.GameTypeId
import com.hfu.bierolympiade.feature.player.ui.PlayerUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddGameViewModel @Inject constructor(
    val getGameTypeByIdUseCase: GetGameTypeByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun bindUi(context: Context): LiveData<String?> = liveData {
        emit(null)
        val result = getGameTypeByIdUseCase(GameTypeId(savedStateHandle.get("id")?:""))?.rules
        emit(result)
    }
}