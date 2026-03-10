package com.uno.orderservice.domain.outbox

enum class OutboxStatus {
    INIT,
    PUBLISHED,
    FAILED,
}
