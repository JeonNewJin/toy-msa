package com.uno.userservice.domain

class UserCommand {
    data class Create(
        val email: String,
        val name: String,
        val password: String
    )
}