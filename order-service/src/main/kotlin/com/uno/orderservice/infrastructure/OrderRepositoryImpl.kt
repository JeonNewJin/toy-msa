package com.uno.orderservice.infrastructure

import com.uno.orderservice.domain.Order
import com.uno.orderservice.domain.OrderRepository
import org.springframework.stereotype.Component

@Component
class OrderRepositoryImpl(
    private val orderJpaRepository: OrderJpaRepository
) : OrderRepository {
    override fun save(order: Order): Order = orderJpaRepository.save(order)

    override fun findByOrderId(orderId: String): Order? = orderJpaRepository.findByOrderId(orderId)

    override fun findAllByUserId(userId: String): List<Order> = orderJpaRepository.findAllByUserId(userId)
}