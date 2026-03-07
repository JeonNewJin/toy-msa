package com.uno.orderservice.domain

interface OrderRepository {
    fun save(order: Order): Order
    fun findByOrderId(orderId: String): Order?
    fun findAllByUserId(userId: String): List<Order>
}