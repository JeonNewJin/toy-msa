package com.uno.infrastructure.order

import com.uno.domain.order.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<Order, Int> {
    fun findByOrderId(orderId: String): Order?

    fun findAllByUserId(userId: String): List<Order>
}
