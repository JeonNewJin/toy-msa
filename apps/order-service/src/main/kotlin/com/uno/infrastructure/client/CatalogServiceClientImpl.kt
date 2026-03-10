package com.uno.infrastructure.client

import com.uno.domain.order.CatalogServiceClient
import org.springframework.stereotype.Component

@Component
class CatalogServiceClientImpl(
    private val catalogServiceGrpcClient: CatalogServiceGrpcClient,
) : CatalogServiceClient {
    override fun reserveStock(request: CatalogServiceClient.Request.ReserveStock) =
        catalogServiceGrpcClient.reserveStock(request)
}
