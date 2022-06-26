package com.hfu.bierolympiade.feature.addplayertoevent.ui

import com.hfu.bierolympiade.domain.model.EventId
import com.hfu.bierolympiade.domain.model.PlayerId

class AddPlayerToEventUI(
    val id: PlayerId,
    val eventId: EventId,
    val name: String,
)