package com.uno.userservice.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional(readOnly = true)
@Service
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {
    @Transactional
    fun create(command: UserCommand.Create): UserInfo {
        val user = User(
            userId = UUID.randomUUID().toString(),
            email = command.email,
            name = command.name,
            passwordHash = passwordEncoder.encode(command.password)
        )
        userRepository.save(user)
        return UserInfo.from(user)
    }

    fun findById(id: Long): UserInfo =
        userRepository.findById(id)
            ?.let { UserInfo.from(it) }
            ?: throw IllegalArgumentException("User Not Found")
}