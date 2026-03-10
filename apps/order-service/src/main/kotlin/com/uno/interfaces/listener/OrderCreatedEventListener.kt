package com.uno.interfaces.listener

import com.uno.domain.order.OrderEvent
import com.uno.domain.order.OrderEventPublisher
import com.uno.domain.outbox.OutboxService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class OrderCreatedEventListener(
    private val outboxService: OutboxService,
    private val orderEventPublisher: OrderEventPublisher,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun createOutbox(event: OrderEvent.Created) {
        outboxService.create(event.toOutboxCommand())
    }

    @Async("messagePublishEventExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun publishEvent(event: OrderEvent.Created) {
        if (orderEventPublisher.publish(event)) {
            outboxService.published(event.eventId)
        } else {
            outboxService.failed(event.eventId)
        }
    }
}
