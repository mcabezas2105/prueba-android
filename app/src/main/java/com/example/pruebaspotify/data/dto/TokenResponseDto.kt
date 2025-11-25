package com.example.pruebaspotify.data.dto


import com.example.pruebaspotify.domain.model.Token
import com.google.gson.annotations.SerializedName

data class TokenResponseDto(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("refresh_token") val refreshToken: String?
)

// Mapper a dominio
fun TokenResponseDto.toDomain(): Token {
    return Token(
        accessToken = accessToken,
        refreshToken = refreshToken ?: "",
        expiresIn = expiresIn
    )
}