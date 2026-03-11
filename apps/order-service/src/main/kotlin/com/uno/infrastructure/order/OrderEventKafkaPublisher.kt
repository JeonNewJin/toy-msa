package com.uno.infrastructure.order

import com.uno.domain.order.OrderEvent
import com.uno.domain.order.OrderEventPublisher
import com.uno.domain.outbox.Outbox
import com.uno.event.Event
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderEventKafkaPublisher(
    private val kafkaTemplate: KafkaTemplate<Any, Any>,
) : OrderEventPublisher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun publish(event: OrderEvent.Created): Boolean {
        val payload = Event.of(
            eventId = event.eventId,
            type = event.type,
            payload = event.payload,
        ).toJson()
        return runCatching {
            kafkaTemplate.send(event.type.topic, payload).get()
        }.onFailure {
            log.error("[OrderEventKafkaPublisher.publish] event={}", event, it)
        }.isSuccess
    }

    override fun publishByOutbox(outbox: Outbox): Boolean = runCatching {
            kafkaTemplate.send(outbox.eventType.topic, outbox.payload).get()
        }.onFailure {
            log.error("[OrderEventKafkaPublisher.publishByOutbox] outbox={}", outbox, it)
        }.isSuccess
}
