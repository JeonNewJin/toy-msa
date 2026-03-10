package com.uno.orderservice.domain.order

import com.uno.orderservice.domain.outbox.Outbox

interface OrderEventPublisher {
    fun publish(event: OrderEvent.Created): Boolean
    fun publishByOutbox(outbox: Outbox): Boolean
}
