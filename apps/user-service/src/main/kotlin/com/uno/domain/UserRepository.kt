package com.uno.domain

interface UserRepository {
    fun save(user: com.uno.domain.User): com.uno.domain.User
    fun findById(id: Long): com.uno.domain.User?
    fun findByEmail(email: String): com.uno.domain.User?
}
