package com.uno.infrastructure.security

import com.uno.domain.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurePasswordEncoder(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : PasswordEncoder {
    override fun encode(password: String): String = bCryptPasswordEncoder.encode(password)!!
}
