package com.uno.catalogservice.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CatalogService(
    private val catalogRepository: CatalogRepository
) {
    fun findAll(): List<CatalogInfo> = catalogRepository.findAll().map { CatalogInfo.from(it) }
}