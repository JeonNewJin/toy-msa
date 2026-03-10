package com.uno.orderservice.domain.order

class OrderCommand {
    data class Create(
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val userId: String,
    )
}
