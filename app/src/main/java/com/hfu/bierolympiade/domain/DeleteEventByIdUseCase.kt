package com.hfu.bierolympiade.domain

import android.util.Log
import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DeleteEventByIdUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke(id: EventId) = withContext(Dispatchers.Default) {
        eventRepository.deleteEventById((id))
    }
}