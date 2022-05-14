package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.eventRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetEventsUseCase {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {eventRepo.getAllEvents()}
}