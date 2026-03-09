package com.uno.orderservice.domain.outbox

import com.uno.event.EventPayload
import com.uno.event.EventType

class OutboxCommand {
    data class Create(
        val eventId: String,
        val type: EventType,
        val payload: EventPayload,
    )
}