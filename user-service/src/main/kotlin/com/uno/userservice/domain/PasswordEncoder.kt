package com.uno.userservice.domain

interface PasswordEncoder {
    fun encode(password: String): String
}