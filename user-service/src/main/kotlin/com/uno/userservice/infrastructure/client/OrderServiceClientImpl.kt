package com.uno.userservice.infrastructure.client

import com.uno.userservice.domain.OrderServiceClient
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class OrderServiceClientImpl(
    private val orderServiceFeignClient: OrderServiceFeignClient
) : OrderServiceClient {
    private val log = LoggerFactory.getLogger(javaClass)

    @Bulkhead(name = "order-service")
    @Retry(name = "order-service")
    @CircuitBreaker(name = "order-service", fallbackMethod = "getOrdersFallback")
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

    private fun getOrdersFallback(userId: String, e: Exception): List<OrderServiceClient.Response> {
        log.error("[OrderServiceClientImpl.getOrdersFallback] userId={}", userId, e)
        return emptyList()
    }
}