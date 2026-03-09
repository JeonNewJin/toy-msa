package com.uno.orderservice.domain.outbox

import java.time.LocalDateTime

interface OutboxRepository {
    fun save(outbox: Outbox): Outbox
    fun findByEventId(eventId: String): Outbox?
    fun findRetryTargets(createdAtBefore: LocalDateTime): List<Outbox>
}