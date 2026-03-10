package com.uno.application

import com.uno.domain.order.CatalogServiceClient
import com.uno.domain.order.OrderCommand
import com.uno.domain.order.OrderInfo
import com.uno.domain.order.OrderService
import org.springframework.stereotype.Component

@Component
class OrderFacade(
    private val catalogServiceClient: CatalogServiceClient,
    private val orderService: OrderService,
) {
    fun createOrder(command: OrderCommand.Create): OrderInfo {
        catalogServiceClient.reserveStock(CatalogServiceClient.Request.ReserveStock.from(command))
        return orderService.create(command)
    }
}
