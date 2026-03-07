package com.uno.userservice.infrastructure.user

import com.uno.userservice.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {
}