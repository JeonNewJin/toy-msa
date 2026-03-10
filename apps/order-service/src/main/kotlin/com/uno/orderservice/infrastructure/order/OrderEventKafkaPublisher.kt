package com.uno.orderservice.infrastructure.order

import com.uno.event.Event
import com.uno.orderservice.domain.order.OrderEvent
import com.uno.orderservice.domain.order.OrderEventPublisher
import com.uno.orderservice.domain.outbox.Outbox
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class OrderEventKafkaPublisher(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) : OrderEventPublisher {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun publish(event: OrderEvent.Created): Boolean {
        val payload = Event.of(
            eventId = event.eventId,
            type = event.type,
            payload = event.payload,
        ).toJson()
        return runCatching {
            kafkaTemplate.send(event.type.topic, payload).get(1, TimeUnit.SECONDS)
        }.onFailure {
            log.error("[OrderEventKafkaPublisher.publish] event={}", event, it)
        }.isSuccess
    }

    override fun publishByOutbox(outbox: Outbox): Boolean = runCatching {
            kafkaTemplate.send(outbox.eventType.topic, outbox.payload).get(1, TimeUnit.SECONDS)
        }.onFailure {
            log.error("[OrderEventKafkaPublisher.publishByOutbox] outbox={}", outbox, it)
        }.isSuccess
}
