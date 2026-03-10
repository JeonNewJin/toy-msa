package com.uno.userservice.infrastructure.user

import com.uno.userservice.domain.User
import com.uno.userservice.domain.UserRepository
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {
    override fun save(user: User): User = userJpaRepository.save(user)

    override fun findById(id: Long): User? = userJpaRepository.findById(id).orElse(null)

    override fun findByEmail(email: String): User? = userJpaRepository.findByEmail(email)
}
