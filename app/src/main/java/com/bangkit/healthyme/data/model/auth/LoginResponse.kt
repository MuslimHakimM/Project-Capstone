package com.bangkit.healthyme.data.model.auth

data class LoginResponse(
    val userId: String,
    val name: String,
    val userEmail: String,
    val accessToken: String
)
