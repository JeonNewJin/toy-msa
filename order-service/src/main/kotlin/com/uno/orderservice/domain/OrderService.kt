package com.uno.orderservice.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional(readOnly = true)
@Service
class OrderService(
    val orderRepository: OrderRepository
) {
    @Transactional
    fun create(command: OrderCommand.Create): OrderInfo {
        val order = Order(
            orderId = UUID.randomUUID().toString(),
            productId = command.productId,
            quantity = command.quantity,
            unitPrice = command.unitPrice,
            totalAmount = command.quantity * command.unitPrice,
            userId = command.userId,
        )
        orderRepository.save(order)
        return OrderInfo.from(order)
    }

    fun findByOrderId(orderId: String): OrderInfo =
        orderRepository.findByOrderId(orderId)
            ?.let { OrderInfo.from(it) }
            ?: throw IllegalArgumentException("Order with id $orderId not found")

    fun findAllByUserId(userId: String): List<OrderInfo> =
        orderRepository.findAllByUserId(userId)
            .map { OrderInfo.from(it) }
}