package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        eventRepository.getAllEvents()
    }
}