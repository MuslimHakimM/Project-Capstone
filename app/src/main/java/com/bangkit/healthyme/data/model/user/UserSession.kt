package com.bangkit.healthyme.data.model.user

data class UserSession(
    val userId: String,
    val name: String,
    val userEmail: String,
    val accessToken: String,
    val isLogin: Boolean
)