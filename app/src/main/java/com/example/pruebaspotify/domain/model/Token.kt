package com.example.pruebaspotify.domain.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int
)