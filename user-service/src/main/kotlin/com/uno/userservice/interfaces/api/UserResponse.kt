package com.uno.userservice.interfaces.api

import com.uno.userservice.domain.UserInfo

data class UserResponse(
    val userId: String,
    val email: String,
    val name: String,
) {
    companion object {
        fun from(info: UserInfo): UserResponse =
            UserResponse(
                userId = info.userId,
                email = info.email,
                name = info.name,
            )
    }
}