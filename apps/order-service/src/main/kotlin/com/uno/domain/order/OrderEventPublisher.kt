package com.uno.domain.order

import com.uno.domain.outbox.Outbox

interface OrderEventPublisher {
    fun publish(event: OrderEvent.Created): Boolean
    fun publishByOutbox(outbox: Outbox): Boolean
}
