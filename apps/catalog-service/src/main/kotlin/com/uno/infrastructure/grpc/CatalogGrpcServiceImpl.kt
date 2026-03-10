package com.uno.infrastructure.grpc

import com.uno.cataloggrpc.CatalogServiceGrpc
import com.uno.cataloggrpc.ReserveStockRequest
import com.uno.cataloggrpc.ReserveStockResponse
import com.uno.domain.CatalogService
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CatalogGrpcServiceImpl(
    private val catalogService: CatalogService,
) : CatalogServiceGrpc.CatalogServiceImplBase() {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun reserveStock(
        request: ReserveStockRequest,
        responseObserver: StreamObserver<ReserveStockResponse>,
    ) {
        log.info("[CatalogGrpcServiceImpl.reserveStock] productId={}, quantity={}", request.productId, request.quantity)
        val response = try {
            catalogService.reserveStock(request.productId, request.quantity)
            ReserveStockResponse.newBuilder()
                .setSuccess(true)
                .setMessage("재고 예약 성공")
                .build()
        } catch (e: IllegalArgumentException) {
            log.warn("[CatalogGrpcServiceImpl.reserveStock] 재고 예약 실패: {}", e.message)
            ReserveStockResponse.newBuilder()
                .setSuccess(false)
                .setMessage(e.message ?: "재고 예약 실패")
                .build()
        }
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
