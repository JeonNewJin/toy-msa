package com.uno.config

import brave.Tracing
import brave.grpc.GrpcTracing
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcTracingConfig {
    @Bean
    fun grpcTracing(tracing: Tracing): GrpcTracing = GrpcTracing.create(tracing)
}
