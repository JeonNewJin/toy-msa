package com.uno.userservice.interfaces.api

import com.uno.userservice.domain.UserCommand
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class UserRequest {
    data class Create(
        @Email
        val email: String,
        @NotBlank
        val name: String,
        @NotBlank
        val password: String,
    ) {
        fun toCommand(): UserCommand.Create =
            UserCommand.Create(
                email = email,
                name = name,
                password = password,
            )
    }
}
