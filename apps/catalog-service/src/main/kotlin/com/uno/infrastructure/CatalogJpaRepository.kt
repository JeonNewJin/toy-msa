package com.uno.infrastructure

import com.uno.domain.Catalog
import jakarta.persistence.LockModeType.PESSIMISTIC_WRITE
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface CatalogJpaRepository : JpaRepository<Catalog, Long> {
    @Lock(PESSIMISTIC_WRITE)
    @Query("select c from Catalog c where c.productId = :productId")
    fun findByProductIdWithLock(productId: String): Catalog?
}
