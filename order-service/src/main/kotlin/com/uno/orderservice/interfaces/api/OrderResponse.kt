package com.uno.orderservice.interfaces.api

import com.uno.orderservice.domain.order.OrderInfo

data class OrderResponse(
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalAmount: Int,
    val userId: String,
) {
    companion object {
        fun from(info: OrderInfo): OrderResponse =
            OrderResponse(
                orderId = info.orderId,
                productId = info.productId,
                quantity = info.quantity,
                unitPrice = info.unitPrice,
                totalAmount = info.totalAmount,
                userId = info.userId,
            )
    }
}
