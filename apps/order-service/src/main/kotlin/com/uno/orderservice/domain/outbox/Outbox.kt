package com.uno.orderservice.domain.outbox

import com.uno.event.Event
import com.uno.event.EventType
import com.uno.orderservice.domain.outbox.OutboxStatus.FAILED
import com.uno.orderservice.domain.outbox.OutboxStatus.INIT
import com.uno.orderservice.domain.outbox.OutboxStatus.PUBLISHED
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "outbox")
@Entity
class Outbox private constructor(
    eventId: String,
    eventType: EventType,
    payload: String,
    status: OutboxStatus,
    createdAt: LocalDateTime,
) {
    companion object {
        fun create(command: OutboxCommand.Create): Outbox =
            Outbox(
                eventId = command.eventId,
                eventType = command.type,
                payload = Event.of(
                    eventId = command.eventId,
                    type = command.type,
                    payload = command.payload,
                ).toJson(),
                status = INIT,
                createdAt = LocalDateTime.now(),
            )
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0

    @Column(nullable = false, unique = true)
    val eventId: String = eventId

    @Enumerated(STRING)
    @Column(nullable = false)
    val eventType: EventType = eventType

    @Column(nullable = false, columnDefinition = "TEXT")
    val payload: String = payload

    @Enumerated(STRING)
    @Column(nullable = false)
    var status: OutboxStatus = status
        private set

    @Column(nullable = false)
    val createdAt: LocalDateTime = createdAt

    fun published() {
        status = PUBLISHED
    }

    fun failed() {
        status = FAILED
    }
}
