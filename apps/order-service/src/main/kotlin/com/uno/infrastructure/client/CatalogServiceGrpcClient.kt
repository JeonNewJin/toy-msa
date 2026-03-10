package com.uno.infrastructure.client

import com.uno.cataloggrpc.CatalogServiceGrpc
import com.uno.cataloggrpc.ReserveStockRequest
import com.uno.domain.order.CatalogServiceClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CatalogServiceGrpcClient(
    private val stub: CatalogServiceGrpc.CatalogServiceBlockingStub,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun reserveStock(request: CatalogServiceClient.Request.ReserveStock) {
        log.info("[CatalogServiceGrpcClient.reserveStock] productId={}, quantity={}", request.productId, request.quantity)
        val request = ReserveStockRequest.newBuilder()
            .setProductId(request.productId)
            .setQuantity(request.quantity)
            .build()
        val response = stub.reserveStock(request)
        if (!response.success) {
            throw IllegalStateException(response.message)
        }
    }
}
