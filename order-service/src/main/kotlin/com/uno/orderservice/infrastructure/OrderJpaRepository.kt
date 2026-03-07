package com.uno.orderservice.infrastructure

import com.uno.orderservice.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<Order, Int> {
    fun findByOrderId(orderId: String): Order?

    fun findAllByUserId(userId: String): List<Order>
}