package com.uno.catalogservice.domain

interface CatalogRepository {
    fun findAll(): List<Catalog>
    fun findByProductIdWithLock(productId: String): Catalog?
    fun save(catalog: Catalog): Catalog
}
