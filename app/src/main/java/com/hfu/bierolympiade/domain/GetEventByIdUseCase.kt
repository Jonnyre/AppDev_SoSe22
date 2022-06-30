package com.hfu.bierolympiade.domain

import android.util.Log
import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.domain.model.EventId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke(eventId: EventId) = withContext(Dispatchers.Default) {
        eventRepository.getEventById(eventId)
    }
}