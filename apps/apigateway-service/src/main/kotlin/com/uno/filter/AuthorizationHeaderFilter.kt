package com.uno.filter

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthorizationHeaderFilter(
    @Value($$"${token.secret}") private val secret: String,
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    private val log = LoggerFactory.getLogger(javaClass)
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    override fun apply(config: Config): GatewayFilter = GatewayFilter { exchange, chain ->
        val request = exchange.request
        val authHeader = request.headers[AUTHORIZATION]?.firstOrNull()

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return@GatewayFilter onError(exchange, "Authorization header is missing or invalid")
        }

        val token = authHeader.removePrefix("Bearer ")

        if (!isValidToken(token)) {
            return@GatewayFilter onError(exchange, "JWT token is invalid")
        }

        chain.filter(exchange)
    }

    private fun isValidToken(token: String): Boolean =
        runCatching {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
            true
        }.getOrElse {
            log.error("JWT validation failed: {}", it.message)
            false
        }

    private fun onError(exchange: ServerWebExchange, message: String): Mono<Void> {
        log.error("Authorization error: {}", message)
        exchange.response.statusCode = UNAUTHORIZED
        return exchange.response.setComplete()
    }

    class Config
}
