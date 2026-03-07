package com.uno.catalogservice.domain

interface CatalogRepository {
    fun findAll(): List<Catalog>
}