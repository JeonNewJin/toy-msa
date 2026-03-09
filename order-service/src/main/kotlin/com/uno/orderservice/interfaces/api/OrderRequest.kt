package com.uno.orderservice.interfaces.api

import com.uno.orderservice.domain.order.OrderCommand

class OrderRequest {
    data class Create(
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val userId: String,
    ) {
        fun toCommand(): OrderCommand.Create =
            OrderCommand.Create(
                productId = productId,
                quantity = quantity,
                unitPrice = unitPrice,
                userId = userId,
            )
    }
}