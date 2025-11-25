package com.example.pruebaspotify.data.mappers


import com.example.pruebaspotify.data.dto.TokenResponseDto
import com.example.pruebaspotify.domain.model.Token

fun TokenResponseDto.toDomain(): Token {
    return Token(
        accessToken = accessToken,
        refreshToken = refreshToken ?: "",
        expiresIn = expiresIn
    )
}