package com.uno.orderservice.domain.outbox

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class OutboxService(
    private val outboxRepository: OutboxRepository,
) {
    @Transactional
    fun create(command: OutboxCommand.Create) {
        val outbox = Outbox.create(command)
        outboxRepository.save(outbox)
    }

    @Transactional
    fun published(eventId: String) {
        val outbox = outboxRepository.findByEventId(eventId)
            ?: throw IllegalArgumentException("[eventId = $eventId] Outbox를 찾을 수 없습니다.")
        outbox.published()
    }

    @Transactional
    fun failed(eventId: String) {
        val outbox = outboxRepository.findByEventId(eventId)
            ?: throw IllegalArgumentException("[eventId = $eventId] Outbox를 찾을 수 없습니다.")
        outbox.failed()
    }

    fun findRetryTargets(createdAtBefore: LocalDateTime): List<Outbox> =
        outboxRepository.findRetryTargets(createdAtBefore)
}