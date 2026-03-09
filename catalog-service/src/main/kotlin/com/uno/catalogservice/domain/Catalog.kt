package com.uno.catalogservice.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "catalog")
@Entity
class Catalog(
    productId: String,
    productName: String,
    stock: Int,
    unitPrice: Int
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0

    @Column(nullable = false, unique = true)
    val productId: String = productId

    @Column(nullable = false)
    val productName: String = productName

    @Column(nullable = false)
    var stock: Int = stock
        private set

    @Column(nullable = false)
    val unitPrice: Int = unitPrice

    fun deductStock(quantity: Int) {
        require(stock >= quantity) { "재고가 부족합니다. productId = $productId, stock = $stock, quantity = $quantity" }
        stock -= quantity
    }
}