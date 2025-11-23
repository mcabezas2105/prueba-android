package com.example.pruebaspotify.data
import java.security.MessageDigest
import java.util.Base64
import java.util.UUID

object PKCEHelper {

    fun generateCodeVerifier(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }

    fun generateCodeChallenge(codeVerifier: String): String {
        val bytes = codeVerifier.toByteArray(Charsets.US_ASCII)
        val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
        // Usa android.util.Base64 y reemplaza caracteres para URL-safe
        return android.util.Base64.encodeToString(digest, android.util.Base64.URL_SAFE or android.util.Base64.NO_PADDING or android.util.Base64.NO_WRAP)
    }

    fun getSpotifyLoginUrl(clientId: String, redirectUri: String, codeChallenge: String): String {
        return "https://accounts.spotify.com/authorize" +
                "?client_id=$clientId" +
                "&response_type=code" +
                "&redirect_uri=$redirectUri" +
                "&code_challenge_method=S256" +
                "&code_challenge=$codeChallenge" +
                "&scope=user-read-private%20user-read-email"
    }
}