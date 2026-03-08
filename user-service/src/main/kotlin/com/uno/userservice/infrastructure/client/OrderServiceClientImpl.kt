package com.uno.userservice.infrastructure.client

import com.uno.userservice.domain.OrderServiceClient
import org.springframework.stereotype.Component

@Component
class OrderServiceClientImpl(
    private val orderServiceFeignClient: OrderServiceFeignClient
) : OrderServiceClient {
    override fun getOrders(userId: String): List<OrderServiceClient.Response> =
        orderServiceFeignClient.getOrders(userId).map {
            OrderServiceClient.Response(
                orderId = it.orderId,
                productId = it.productId,
                quantity = it.quantity,
                unitPrice = it.unitPrice,
                totalAmount = it.totalAmount,
            )
        }
}