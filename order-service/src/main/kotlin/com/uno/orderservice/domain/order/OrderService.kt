package com.uno.orderservice.domain.order

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional(readOnly = true)
@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
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

        applicationEventPublisher.publishEvent(OrderEvent.Created.from(order))

        return OrderInfo.from(order)
    }

    fun findByOrderId(orderId: String): OrderInfo =
        orderRepository.findByOrderId(orderId)
            ?.let { OrderInfo.from(it) }
            ?: throw IllegalArgumentException("[orderId = $orderId] 주문을 찾을 수 없습니다.")

    fun findAllByUserId(userId: String): List<OrderInfo> =
        orderRepository.findAllByUserId(userId)
            .map { OrderInfo.from(it) }
}