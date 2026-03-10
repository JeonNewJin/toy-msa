package com.uno.catalogservice.domain.event

import com.uno.catalogservice.domain.CatalogRepository
import com.uno.event.Event
import com.uno.event.EventPayload
import com.uno.event.EventType.ORDER_CREATED
import com.uno.event.payload.OrderCreatedEventPayload
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class OrderCreatedEventHandler(
    private val catalogRepository: CatalogRepository,
) : EventHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun handle(event: Event<out EventPayload>) {
        log.info("[OrderCreatedEventHandler.handle] event={}", event)
        val payload = event.payload as OrderCreatedEventPayload
        val catalog = (
            catalogRepository.findByProductIdWithLock(payload.productId)
            ?: throw IllegalArgumentException("[productId = ${payload.productId}] 상품을 찾을 수 없습니다.")
        )
        catalog.deductStock(payload.quantity)
        catalogRepository.save(catalog)
    }

    override fun supports(event: Event<out EventPayload>): Boolean = ORDER_CREATED == event.type
}
