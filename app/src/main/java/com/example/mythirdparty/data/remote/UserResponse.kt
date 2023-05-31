package com.example.mythirdparty.data.remote

data class UserResponse(
    val items: ArrayList<User>
)

data class User(
    val login: String? = null,
    val id: Int? = null,
    val avatar_url: String? = null
)