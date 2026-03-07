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
    val stock: Int = stock

    @Column(nullable = false)
    val unitPrice: Int = unitPrice
}