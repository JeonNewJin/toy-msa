package com.uno.domain

class UserCommand {
    data class Create(
        val email: String,
        val name: String,
        val password: String,
    )
}
