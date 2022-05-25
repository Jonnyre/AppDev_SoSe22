package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.EventRepository
import com.hfu.bierolympiade.domain.model.Event
import com.hfu.bierolympiade.domain.model.EventId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddDemoEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        if (eventRepository.getAllEvents().isNotEmpty()) return@withContext

        listOfNotNull(
            Event.create(
                id = EventId("a59c0e7b-3a58-4859-934d-1a0393835637"),
                name = "Bierolympiade",
                location = "Furtwangen",
                date = "06.05.22",
                participants = 2,
                fees = 10
            ),
            Event.create(
                id = EventId("f16cdf15-6528-4a0b-993c-24d5bf8045a7"),
                name = "Kicker Turnier",
                location = "Speicher",
                date = "01.01.2222",
                participants = 557,
                fees = null

            ),
            Event.create(
                id = EventId("f16cdf15-6528-4a0b-993c-24d5bf8046a7"),
                name = "Ein weiteres Event",
                location = "Zuhause",
                date = "01.01.2222",
                participants = 1,
                        fees = null
            ),
            Event.create(
                id = EventId("867e5af2-aa53-4e46-9cfd-a1bc9b8929cb"),
                name = "Ewiges Bierpong Match",
                location = "Alte Cafete",
                date = "05.05.22",
                participants = 4,
                fees = 15
            ),
        ).forEach {
            eventRepository.addEvent(it)
        }
    }
}