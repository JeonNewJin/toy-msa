package com.uno.orderservice.interfaces.api

import com.uno.orderservice.domain.order.OrderService
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service")
class OrderController(
    private val env: Environment,
    private val orderService: OrderService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/health-check")
    fun status(): String =
        String.format(
            "It's Working in Order Service"
                    + ", port(local.server.port)=" + env.getProperty("local.server.port")
                    + ", port(server.port)=" + env.getProperty("server.port")
        )

    @PostMapping("/orders")
    fun createOrder(
        @RequestBody request: OrderRequest.Create
    ): ResponseEntity<OrderResponse> {
        log.info("before adding order data")
        val order = orderService.create(request.toCommand())
        log.info("after adding order data")
        return order
            .let { ResponseEntity.status(CREATED).body(OrderResponse.from(it)) }
    }

    @GetMapping("/orders")
    fun getOrder(
        @RequestParam userId: String,
    ): ResponseEntity<List<OrderResponse>> {
        log.info("before retrieving order data")
        val orders = orderService.findAllByUserId(userId)
        log.info("after retrieving order data")
        return orders
            .map { OrderResponse.from(it) }
            .let { ResponseEntity.ok(it) }
    }
}