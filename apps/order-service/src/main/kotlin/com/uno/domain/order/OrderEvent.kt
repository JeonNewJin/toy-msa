package com.uno.domain.order

import com.uno.event.EventPayload
import com.uno.event.EventType
import com.uno.event.EventType.ORDER_CREATED
import com.uno.event.payload.OrderCreatedEventPayload
import com.uno.domain.outbox.OutboxCommand
import java.util.*

class OrderEvent {
    data class Created(
        val eventId: String,
        val type: EventType,
        val payload: EventPayload,
    ) {
        companion object {
            fun from(order: Order): Created =
                Created(
                    eventId = UUID.randomUUID().toString(),
                    type = ORDER_CREATED,
                    payload = OrderCreatedEventPayload(
                        orderId = order.orderId,
                        productId = order.productId,
                        quantity = order.quantity,
                        unitPrice = order.unitPrice,
                        totalAmount = order.totalAmount,
                        userId = order.userId,
                    ),
                )
        }

        fun toOutboxCommand(): OutboxCommand.Create =
            OutboxCommand.Create(
                eventId = eventId,
                type = type,
                payload = payload,
            )
    }
}
