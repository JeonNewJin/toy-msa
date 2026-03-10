package com.uno.domain.order

data class OrderInfo(
    val id: Long,
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalAmount: Int,
    val userId: String,
) {
    companion object {
        fun from(order: Order): OrderInfo =
            OrderInfo(
                id = order.id,
                orderId = order.orderId,
                productId = order.productId,
                quantity = order.quantity,
                unitPrice = order.unitPrice,
                totalAmount = order.totalAmount,
                userId = order.userId,
            )
    }
}
