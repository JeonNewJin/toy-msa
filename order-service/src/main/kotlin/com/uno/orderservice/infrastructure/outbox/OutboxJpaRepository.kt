package com.uno.orderservice.infrastructure.outbox

import com.uno.orderservice.domain.outbox.Outbox
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface OutboxJpaRepository : JpaRepository<Outbox, Long> {
    fun findByEventId(eventId: String): Outbox?

    @Query("SELECT o FROM Outbox o WHERE o.status = 'FAILED' OR (o.status = 'INIT' AND o.createdAt < :createdAtBefore)")
    fun findRetryTargets(createdAtBefore: LocalDateTime): List<Outbox>
}