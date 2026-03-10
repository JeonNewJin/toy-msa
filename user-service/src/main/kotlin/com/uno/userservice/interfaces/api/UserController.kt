package com.uno.userservice.interfaces.api

import com.uno.userservice.domain.UserService
import io.micrometer.core.annotation.Timed
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val env: Environment,
    private val greeting: Greeting,
    private val userService: UserService
) {
    @GetMapping("/health-check")
    @Timed(value = "users.status", longTask = true)
    fun status(): String =
        String.format(
            "It's Working in User Service"
                    + ", port(local.server.port)=" + env.getProperty("local.server.port")
                    + ", port(server.port)=" + env.getProperty("server.port")
                    + ", welcome message=" + env.getProperty("greeting.message")
                    + ", gateway ip(env)=" + env.getProperty("gateway.ip")
                    + ", token secret=" + env.getProperty("token.secret")
                    + ", token expiration time=" + env.getProperty("token.expiration_time")
        )

    @GetMapping("/welcome")
    @Timed(value = "users.welcome", longTask = true)
    fun welcome(
        request: HttpServletRequest,
    ): String {
        print(
            "users.welcome ip:" + request.remoteAddr +
                    "," + request.remoteHost +
                    "," + request.requestURI +
                    "," + request.requestURL
        )
        return greeting.message
    }

    @PostMapping("/users")
    fun createUser(
        @RequestBody request: UserRequest.Create
    ): ResponseEntity<UserResponse> =
        userService.create(request.toCommand())
            .let { ResponseEntity.status(CREATED).body(UserResponse.from(it)) }

    @GetMapping("/users/{userId}")
    fun getUser(
        @PathVariable userId: Long
    ): ResponseEntity<UserResponse> =
        userService.findById(userId)
            .let { ResponseEntity.ok(UserResponse.from(it)) }
}