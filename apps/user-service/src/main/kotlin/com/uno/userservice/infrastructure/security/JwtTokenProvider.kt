package com.uno.userservice.infrastructure.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value($$"${token.secret}") private val secret: String,
    @Value($$"${token.expiration_time}") private val expiration: Long,
) {
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(userId: String): String {
        val now = Date()
        return Jwts.builder()
            .subject(userId)
            .issuedAt(now)
            .expiration(Date(now.time + expiration))
            .signWith(key)
            .compact()
    }
}
