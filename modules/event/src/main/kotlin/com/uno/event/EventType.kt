package com.uno.event

import com.uno.event.EventType.Topic.ORDER_CREATED_V1
import com.uno.event.payload.OrderCreatedEventPayload
import org.slf4j.LoggerFactory

enum class EventType(
    val payloadClass: Class<out EventPayload>,
    val topic: String,
) {
    ORDER_CREATED(OrderCreatedEventPayload::class.java, ORDER_CREATED_V1);

    companion object {
        private val log = LoggerFactory.getLogger(EventType::class.java)

        fun from(type: String): EventType =
            try {
                valueOf(type)
            } catch (e: Exception) {
                log.error("[EventType.from] type={}", type, e)
                throw e
            }
    }

    object Topic {
        const val ORDER_CREATED_V1 = "order.created.v1"
    }
}
