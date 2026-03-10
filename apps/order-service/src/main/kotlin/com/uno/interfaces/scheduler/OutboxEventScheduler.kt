package com.uno.interfaces.scheduler

import com.uno.domain.order.OrderEventPublisher
import com.uno.domain.outbox.Outbox
import com.uno.domain.outbox.OutboxService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OutboxEventScheduler(
    private val outboxService: OutboxService,
    private val orderEventPublisher: OrderEventPublisher,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedDelay = 60_000)
    fun retry() {
        val targets = outboxService.findRetryTargets(LocalDateTime.now().minusMinutes(10))
        if (targets.isEmpty()) return

        log.info("[OutboxEventScheduler.retry] targets={}", targets.size)
        targets.forEach { outbox -> retry(outbox) }
    }

    private fun retry(outbox: Outbox) {
        if (orderEventPublisher.publishByOutbox(outbox)) {
            outboxService.published(outbox.eventId)
        } else {
            outboxService.failed(outbox.eventId)
        }
    }
}
