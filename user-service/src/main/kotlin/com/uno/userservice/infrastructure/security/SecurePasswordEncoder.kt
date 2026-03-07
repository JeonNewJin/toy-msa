package com.uno.userservice.infrastructure.security

import com.uno.userservice.domain.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurePasswordEncoder : PasswordEncoder {
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun encode(password: String): String = bCryptPasswordEncoder.encode(password)!!
}