package com.uno.apigatewayservice.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun apply(config: Config): GatewayFilter = GatewayFilter { exchange, chain ->
        val request: ServerHttpRequest = exchange.request
        val response: ServerHttpResponse = exchange.response

        log.info("Global Filter baseMessage: {}, {}", config.baseMessage, request.remoteAddress)

        if (config.preLogger) {
            log.info("Global Filter Start: - request id: {}", request.id)
        }

        chain.filter(exchange).then(Mono.fromRunnable {
            if (config.postLogger) {
                log.info("Global Filter End: - response code: {}", response.statusCode)
            }
        })
    }

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean,
    )
}