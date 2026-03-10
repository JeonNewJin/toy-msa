package com.uno.domain

import com.uno.domain.event.EventHandler
import com.uno.event.Event
import com.uno.event.EventPayload
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CatalogService(
    private val catalogRepository: CatalogRepository,
    private val eventHandlers: List<EventHandler>,
) {
    fun findAll(): List<CatalogInfo> = catalogRepository.findAll().map { CatalogInfo.from(it) }

    @Transactional
    fun reserveStock(productId: String, quantity: Int) {
        val catalog = catalogRepository.findByProductIdWithLock(productId)
            ?: throw IllegalArgumentException("[$productId] 상품을 찾을 수 없습니다.")
        catalog.reserve(quantity)
        catalogRepository.save(catalog)
    }

    @Transactional
    fun handleEvent(event: Event<EventPayload>) {
        val handler = eventHandlers.find { it.supports(event) }
            ?: throw IllegalArgumentException("Event handler not found: $event")
        handler.handle(event)
    }
}
