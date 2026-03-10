package com.uno.domain.outbox

enum class OutboxStatus {
    INIT,
    PUBLISHED,
    FAILED,
}
