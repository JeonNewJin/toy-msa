package com.uno.userservice

import feign.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients
class UserServiceApplication {
    @Bean
    fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL
}

fun main(args: Array<String>) {
    runApplication<UserServiceApplication>(*args)
}