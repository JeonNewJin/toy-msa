package com.uno.config

import brave.grpc.GrpcTracing
import com.uno.cataloggrpc.CatalogServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcClientConfig(
    @Value($$"${grpc.client.catalog-service.host:localhost}") private val host: String,
    @Value($$"${grpc.client.catalog-service.port:9091}") private val port: Int,
) {
    private lateinit var channel: ManagedChannel

    @Bean
    fun catalogGrpcChannel(grpcTracing: GrpcTracing): ManagedChannel {
        channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext()
            .intercept(grpcTracing.newClientInterceptor())
            .build()
        return channel
    }

    @Bean
    fun catalogGrpcStub(catalogGrpcChannel: ManagedChannel): CatalogServiceGrpc.CatalogServiceBlockingStub =
        CatalogServiceGrpc.newBlockingStub(catalogGrpcChannel)

    @PreDestroy
    fun shutdown() {
        if (::channel.isInitialized) channel.shutdown()
    }
}
