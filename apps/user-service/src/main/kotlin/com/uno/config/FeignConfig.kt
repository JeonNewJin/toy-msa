package com.uno.config

import feign.Logger
import feign.Request
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class FeignConfig {
    @Bean
    fun feignRequestOptions(): Request.Options =
        Request.Options(
            3,
            TimeUnit.SECONDS, // 연결 타임아웃
            5,
            TimeUnit.SECONDS, // 읽기 타임아웃
            true,
        )

    @Bean
    fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL
}
