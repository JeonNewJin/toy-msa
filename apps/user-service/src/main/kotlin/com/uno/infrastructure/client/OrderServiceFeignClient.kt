package com.uno.infrastructure.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("order-service")
interface OrderServiceFeignClient {

    @GetMapping("/order-service/orders")
    fun getOrders(@RequestParam userId: String): List<Response>

    data class Response(
        val orderId: String,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalAmount: Int,
        val userId: String,
    )
}
