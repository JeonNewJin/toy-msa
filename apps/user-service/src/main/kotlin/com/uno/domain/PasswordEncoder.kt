package com.uno.domain

interface PasswordEncoder {
    fun encode(password: String): String
}
