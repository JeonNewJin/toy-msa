package com.uno.userservice.domain

interface OrderServiceClient {
    fun getOrders(userId: String): List<Response>

    data class Response(
        val orderId: String,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalAmount: Int,
    )
}