package com.uno.firstservice

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first-service")
class FirstServiceController(
    private val env: Environment,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/welcome")
    fun welcome() = "Welcome to the First service."

    @GetMapping("/message")
    fun message(
        @RequestHeader("first-request") header: String,
    ): String {
        log.info(header)
        return "Hello World in First Service."
    }

    @GetMapping("/check")
    fun check(
        request: HttpServletRequest,
    ): String {
        log.info("Server port={}", request.serverPort)
        return String.format(
            "Hi, there. This is a message from First Service on PORT %s",
            env.getProperty("local.server.port"),
        )
    }
}
