package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.domain.model.EventId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteEventByIdUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke(id: EventId) = withContext(Dispatchers.Default) {
        eventRepository.deleteEventById((id))
    }
}