package com.hfu.bierolympiade.domain

import android.util.Log
import com.hfu.bierolympiade.data.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        Timber.log(Log.INFO, "Get All Events")
        eventRepository.getAllEvents()
    }
}