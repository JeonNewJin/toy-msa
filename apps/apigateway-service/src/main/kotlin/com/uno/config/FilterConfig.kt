package com.uno.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

//    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator =
        builder.routes()
            .route("first-service") { r ->
                r.path("/first-service/**")
                    .filters { f ->
                        f.addRequestHeader("first-request", "1st-request-header")
                         .addResponseHeader("first-response", "1st-response-header")
                    }
                    .uri("http://localhost:8081")
            }
            .route("second-service") { r ->
                r.path("/second-service/**")
                    .filters { f ->
                        f.addRequestHeader("second-request", "2nd-request-header")
                         .addResponseHeader("second-response", "2nd-response-header")
                    }
                    .uri("http://localhost:8082")
            }
            .build()
}
