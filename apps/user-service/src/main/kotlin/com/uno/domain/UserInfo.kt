package com.uno.domain

data class UserInfo(
    val id: Long,
    val userId: String,
    val email: String,
    val name: String,
    val orders: List<OrderInfo> = emptyList(),
) {
    companion object {
        fun from(user: com.uno.domain.User): UserInfo =
            UserInfo(
                id = user.id,
                userId = user.userId,
                email = user.email,
                name = user.name,
            )

        fun from(user: com.uno.domain.User, orders: List<com.uno.domain.OrderServiceClient.Response>): UserInfo =
            UserInfo(
                id = user.id,
                userId = user.userId,
                email = user.email,
                name = user.name,
                orders = orders.map { OrderInfo.from(it) },
            )
    }

    data class OrderInfo(
        val orderId: String,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalAmount: Int,
    ) {
        companion object {
            fun from(order: com.uno.domain.OrderServiceClient.Response): OrderInfo =
                OrderInfo(
                    orderId = order.orderId,
                    productId = order.productId,
                    quantity = order.quantity,
                    unitPrice = order.unitPrice,
                    totalAmount = order.totalAmount,
                )
        }
    }
}
