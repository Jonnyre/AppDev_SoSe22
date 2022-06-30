package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.data.PlayerEventCrossRefRepository
import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAllFromEventIdUseCase @Inject constructor(
    private val playerEventCrossRefRepository: PlayerEventCrossRefRepository
){
    suspend operator fun invoke(id: EventId) = withContext(Dispatchers.Default) {
        playerEventCrossRefRepository.deleteAllFromEventId(id.value)
    }
}