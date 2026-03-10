package com.uno.interfaces.api

import com.uno.domain.order.OrderCommand

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
