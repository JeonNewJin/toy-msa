package com.uno.orderservice.infrastructure.order

import com.uno.orderservice.domain.order.Order
import com.uno.orderservice.domain.order.OrderRepository
import org.springframework.stereotype.Component

@Component
class OrderRepositoryImpl(
    private val orderJpaRepository: OrderJpaRepository
) : OrderRepository {
    override fun save(order: Order): Order = orderJpaRepository.save(order)

    override fun findByOrderId(orderId: String): Order? = orderJpaRepository.findByOrderId(orderId)

    override fun findAllByUserId(userId: String): List<Order> = orderJpaRepository.findAllByUserId(userId)
}