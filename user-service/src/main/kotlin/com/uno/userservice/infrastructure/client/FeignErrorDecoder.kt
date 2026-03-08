package com.uno.userservice.infrastructure.client

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class FeignErrorDecoder(
    private val env: Environment
) : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        return when (response.status()) {
            400 -> Exception(response.reason())
            404 -> when {
                methodKey.contains("getOrders") -> ResponseStatusException(
                    NOT_FOUND,
                    env.getProperty("order-service.exception.order-is-empty")
                )
                else -> Exception(response.reason())
            }

            else -> Exception(response.reason())
        }
    }
}
