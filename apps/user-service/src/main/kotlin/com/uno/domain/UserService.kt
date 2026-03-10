package com.uno.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional(readOnly = true)
@Service
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val OrderServiceClient: OrderServiceClient,
) {
    @Transactional
    fun create(command: UserCommand.Create): UserInfo {
        val user = User(
            userId = UUID.randomUUID().toString(),
            email = command.email,
            name = command.name,
            passwordHash = passwordEncoder.encode(command.password),
        )
        userRepository.save(user)
        return UserInfo.from(user)
    }

    fun findById(id: Long): UserInfo {
        val user = userRepository.findById(id)
            ?: throw IllegalArgumentException("[id = $id] 사용자를 찾을 수 없습니다.")

        val orders = OrderServiceClient.getOrders(user.userId)

        return UserInfo.from(user, orders)
    }
}
