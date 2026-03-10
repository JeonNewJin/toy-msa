package com.uno.catalogservice.domain

import com.uno.catalogservice.domain.event.EventHandler
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
    fun handleEvent(event: Event<EventPayload>) {
        val handler = eventHandlers.find { it.supports(event) }
            ?: throw IllegalArgumentException("Event handler not found: $event")
        handler.handle(event)
    }
}
