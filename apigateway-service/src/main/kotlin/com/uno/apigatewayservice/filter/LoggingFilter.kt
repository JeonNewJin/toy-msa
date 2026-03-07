package com.uno.apigatewayservice.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java) {

    private val log = LoggerFactory.getLogger(javaClass)

//    override fun apply(config: LoggingFilter.Config): GatewayFilter = GatewayFilter { exchange, chain ->
//        val request: ServerHttpRequest = exchange.request
//        val response: ServerHttpResponse = exchange.response
//
//        log.info("Logging Filter baseMessage: {}, {}", config.baseMessage, request.remoteAddress)
//
//        if (config.preLogger) {
//            log.info("Logging Filter Start - request uri: {}", request.uri)
//        }
//
//        chain.filter(exchange).then(Mono.fromRunnable {
//            if (config.postLogger) {
//                log.info("Logging Filter End - response code: {}", response.statusCode)
//            }
//        })
//    }

    /* 우선 순위를 갖는 Logging Filter 적용 */
    override fun apply(config: Config): GatewayFilter = OrderedGatewayFilter({ exchange, chain ->
        val request: ServerHttpRequest = exchange.request
        val response: ServerHttpResponse = exchange.response

        log.info("Logging Filter baseMessage: {}, {}", config.baseMessage, request.remoteAddress)

        if (config.preLogger) {
            log.info("Logging Filter Start - request uri: {}", request.uri)
        }

        chain.filter(exchange).then(Mono.fromRunnable {
            if (config.postLogger) {
                log.info("Logging Filter End - response code: {}", response.statusCode)
            }
        })
    }, OrderedGatewayFilter.HIGHEST_PRECEDENCE)

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean,
    )
}