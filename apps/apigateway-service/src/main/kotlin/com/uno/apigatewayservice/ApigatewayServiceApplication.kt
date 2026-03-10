package com.uno.apigatewayservice

import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ApigatewayServiceApplication {
    @Bean
    fun httpExchangeRepository(): InMemoryHttpExchangeRepository = InMemoryHttpExchangeRepository()
}

fun main(args: Array<String>) {
    runApplication<ApigatewayServiceApplication>(*args)
}
