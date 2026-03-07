package com.uno.catalogservice.infrastructure

import com.uno.catalogservice.domain.Catalog
import com.uno.catalogservice.domain.CatalogRepository
import org.springframework.stereotype.Component

@Component
class CatalogRepositoryImpl(
    private val catalogJpaRepository: CatalogJpaRepository
) : CatalogRepository {
    override fun findAll(): List<Catalog> = catalogJpaRepository.findAll()
}