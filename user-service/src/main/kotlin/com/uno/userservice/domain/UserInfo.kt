package com.uno.userservice.domain

data class UserInfo(
    val id: Long,
    val userId: String,
    val email: String,
    val name: String,
) {
    companion object {
        fun from(user: User): UserInfo =
            UserInfo(
                id = user.id,
                userId = user.userId,
                email = user.email,
                name = user.name,
            )
    }
}
