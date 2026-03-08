package com.uno.userservice.interfaces.security

import com.uno.userservice.infrastructure.security.JwtTokenProvider
import com.uno.userservice.infrastructure.security.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.util.matcher.IpAddressMatcher

@Configuration
@EnableWebSecurity
class WebSecurity(
    private val userDetailsService: UserDetailsServiceImpl,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
        return builder.build()
    }

    @Bean
    fun configure(
        http: HttpSecurity,
        authenticationManager: AuthenticationManager
    ): SecurityFilterChain {
        val authFilter = AuthenticationFilter(jwtTokenProvider, authenticationManager).apply {
            setFilterProcessesUrl("/login")
        }

        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/users/**",
                    "/login",
                    "/health-check",
                    "/welcome",
                    "/h2-console/**"
                )
                    .access { _, context: RequestAuthorizationContext ->
                        val request = context.request
                        AuthorizationDecision(
                            IpAddressMatcher("127.0.0.1").matches(request) ||
                            IpAddressMatcher("::1").matches(request) ||
                            IpAddressMatcher("192.168.0.44").matches(request)
                        )
                    }
                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilter(authFilter)
            .headers { it.frameOptions { frame -> frame.disable() } }
            .build()
    }
}
