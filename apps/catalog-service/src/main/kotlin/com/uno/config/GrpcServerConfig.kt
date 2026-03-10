package com.uno.config

import brave.grpc.GrpcTracing
import com.uno.infrastructure.grpc.CatalogGrpcServiceImpl
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.ServerInterceptors
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.SmartLifecycle
import org.springframework.stereotype.Component

@Component
class GrpcServerConfig(
    private val catalogGrpcServiceImpl: CatalogGrpcServiceImpl,
    private val grpcTracing: GrpcTracing,
    @Value($$"${grpc.server.port:9091}") private val port: Int,
) : SmartLifecycle {
    private val log = LoggerFactory.getLogger(javaClass)
    private var server: Server? = null
    private var running = false

    override fun start() {
        server = ServerBuilder.forPort(port)
            .addService(ServerInterceptors.intercept(catalogGrpcServiceImpl, grpcTracing.newServerInterceptor()))
            .build()
            .start()
        running = true
        log.info("gRPC server started on port {}", port)
    }

    override fun stop() {
        server?.shutdown()
        running = false
        log.info("gRPC server stopped")
    }

    override fun isRunning(): Boolean = running
}
