package com.uno.orderservice.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "orders")
@Entity
class Order(
    orderId: String,
    productId: String,
    quantity: Int,
    unitPrice: Int,
    totalAmount: Int,
    userId: String,
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = 0

    @Column(nullable = false, unique = true)
    val orderId: String = orderId

    @Column(nullable = false)
    val productId: String = productId

    @Column(nullable = false)
    val quantity: Int = quantity

    @Column(nullable = false)
    val unitPrice: Int = unitPrice

    @Column(nullable = false)
    val totalAmount: Int = totalAmount

    @Column(nullable = false)
    val userId: String = userId
}