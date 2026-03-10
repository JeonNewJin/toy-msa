package com.uno.userservice.interfaces.security

import com.uno.userservice.infrastructure.security.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import tools.jackson.databind.json.JsonMapper

class AuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    authenticationManager: AuthenticationManager,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        val credentials = JsonMapper.builder().findAndAddModules().build()
            .readValue(request.inputStream, LoginRequest::class.java)
        val authToken = UsernamePasswordAuthenticationToken(credentials.email, credentials.password, emptyList())
        return authenticationManager.authenticate(authToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication,
    ) {
        val userId = (authResult.principal as User).username
        val token = jwtTokenProvider.generateToken(userId)
        response.addHeader("Authorization", "Bearer $token")
        response.addHeader("userId", userId)
    }

    data class LoginRequest(val email: String, val password: String)
}
