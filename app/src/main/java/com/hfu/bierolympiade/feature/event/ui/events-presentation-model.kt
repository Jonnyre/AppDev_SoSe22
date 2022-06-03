package com.hfu.bierolympiade.feature.event.ui

import com.hfu.bierolympiade.domain.model.EventId

class EventUI(
    val id: EventId,
    val name: String,
    val location: String,
    val date: String,
)