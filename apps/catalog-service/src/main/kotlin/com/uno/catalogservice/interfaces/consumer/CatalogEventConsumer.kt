package com.uno.catalogservice.interfaces.consumer

import com.uno.catalogservice.domain.CatalogService
import com.uno.event.Event
import com.uno.event.EventType
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class CatalogEventConsumer(
    private val catalogService: CatalogService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @KafkaListener(
        topics = [
            EventType.Topic.ORDER_CREATED_V1,
        ],
        groupId = "catalog-events-consumer",
    )
    fun listen(message: String, ack: Acknowledgment) {
        val event = Event.fromJson(message)
        log.info("[CatalogEventConsumer.listen] message={}", event)
        catalogService.handleEvent(event)
        ack.acknowledge()
    }
}
