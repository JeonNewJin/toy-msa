package com.uno.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "users")
@Entity
class User(
    userId: String,
    email: String,
    name: String,
    passwordHash: String,
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0

    @Column(nullable = false, unique = true)
    val userId: String = userId

    @Column(nullable = false, unique = true)
    val email: String = email

    @Column(nullable = false)
    val name: String = name

    @Column(nullable = false)
    val passwordHash: String = passwordHash
}
