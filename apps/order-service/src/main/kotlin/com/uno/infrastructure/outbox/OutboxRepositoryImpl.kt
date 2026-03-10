package com.uno.infrastructure.outbox

import com.uno.domain.outbox.Outbox
import com.uno.domain.outbox.OutboxRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OutboxRepositoryImpl(
    private val outboxJpaRepository: OutboxJpaRepository,
) : OutboxRepository {
    override fun save(outbox: Outbox): Outbox = outboxJpaRepository.save(outbox)

    override fun findByEventId(eventId: String): Outbox? = outboxJpaRepository.findByEventId(eventId)

    override fun findRetryTargets(createdAtBefore: LocalDateTime): List<Outbox> =
        outboxJpaRepository.findRetryTargets(createdAtBefore)
}
