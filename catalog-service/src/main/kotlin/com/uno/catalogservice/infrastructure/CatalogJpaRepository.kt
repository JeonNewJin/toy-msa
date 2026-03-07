package com.uno.catalogservice.infrastructure

import com.uno.catalogservice.domain.Catalog
import org.springframework.data.jpa.repository.JpaRepository

interface CatalogJpaRepository : JpaRepository<Catalog, Long> {
}