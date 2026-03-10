package com.uno.infrastructure

import com.uno.domain.Catalog
import com.uno.domain.CatalogRepository
import org.springframework.stereotype.Component

@Component
class CatalogRepositoryImpl(
    private val catalogJpaRepository: CatalogJpaRepository,
) : CatalogRepository {
    override fun findAll(): List<Catalog> = catalogJpaRepository.findAll()

    override fun findByProductIdWithLock(productId: String): Catalog? =
        catalogJpaRepository.findByProductIdWithLock(productId)

    override fun save(catalog: Catalog): Catalog = catalogJpaRepository.save(catalog)
}
