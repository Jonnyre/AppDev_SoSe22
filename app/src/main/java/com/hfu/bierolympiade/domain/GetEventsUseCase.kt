package com.hfu.bierolympiade.domain

import com.hfu.bierolympiade.data.eventRepo

class GetEventsUseCase {
    operator fun invoke() = eventRepo.getAllEvents()
}