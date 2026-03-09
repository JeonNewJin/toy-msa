package com.uno.event.payload

import com.uno.event.EventPayload

data class OrderCreatedEventPayload(
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalAmount: Int,
    val userId: String,
) : EventPayload
