package com.uno.userservice.interfaces.api

import com.uno.userservice.domain.UserInfo

data class UserResponse(
    val userId: String,
    val email: String,
    val name: String,
    val orders: List<OrderResponse> = emptyList(),
) {
    companion object {
        fun from(info: UserInfo): UserResponse =
            UserResponse(
                userId = info.userId,
                email = info.email,
                name = info.name,
                orders = info.orders.map { OrderResponse.from(it) },
            )
    }

    data class OrderResponse(
        val orderId: String,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalAmount: Int,
    ) {
        companion object {
            fun from(info: UserInfo.OrderInfo): OrderResponse =
                OrderResponse(
                    orderId = info.orderId,
                    productId = info.productId,
                    quantity = info.quantity,
                    unitPrice = info.unitPrice,
                    totalAmount = info.totalAmount,
                )
        }
    }
}
