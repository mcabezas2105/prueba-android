package com.example.pruebaspotify.presentation.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaspotify.BuildConfig
import com.example.pruebaspotify.data.PKCEHelper
import com.example.pruebaspotify.data.dto.toDomain
import com.example.pruebaspotify.data.repository.AuthRepository
import com.example.pruebaspotify.presentation.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()
    private var codeVerifier: String? = null

    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> get() = _loginState

    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> get() = _accessToken

    private val prefsName = "spotify_auth"
    private val codeVerifierKey = "codeVerifier"

    fun startLogin(context: Context) {
        viewModelScope.launch {
            codeVerifier = PKCEHelper.generateCodeVerifier()

            Log.e("AuthViewModel", "Generando codeVerifier: $codeVerifier")

            saveCodeVerifier(context, codeVerifier!!)

            val codeChallenge = PKCEHelper.generateCodeChallenge(codeVerifier!!)

            val loginUrl = PKCEHelper.getSpotifyLoginUrl(
                clientId = BuildConfig.SPOTIFY_CLIENT_ID,
                redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI,
                codeChallenge = codeChallenge
            )

            Log.e("AuthViewModel", "URL de login generada: $loginUrl")

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(loginUrl))
            context.startActivity(intent)

            _loginState.value = true
        }
    }

    fun exchangeCodeForToken(context: Context, code: String) {
        viewModelScope.launch {

            Log.e("AuthViewModel", "Entrando a exchangeCodeForToken(code=$code)")
            val verifier = loadCodeVerifier(context) ?: return@launch

            Log.e("AuthViewModel", "CodeVerifier cargado desde SharedPrefs: $verifier")
            if (verifier == null) {
                Log.e("AuthViewModel", "CodeVerifier es null")
                return@launch
            }

            try {
                val response = repository.exchangeToken(
                    clientId = BuildConfig.SPOTIFY_CLIENT_ID,
                    grantType = "authorization_code",
                    code = code,
                    redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI,
                    codeVerifier = verifier
                )
                Log.d("AuthViewModel", "Exchange response code: ${response.code()}")

                if (response.isSuccessful) {
                    val token = response.body()?.toDomain()
                    Log.d("AuthViewModel", "Token recibido: ${token?.accessToken}")
                    _accessToken.value = token?.accessToken

                    SessionManager.accessToken = token?.accessToken

                    clearCodeVerifier(context)
                } else {
                    Log.e("AuthViewModel", "Error en exchange token: ${response.errorBody()?.string()}")
                    _accessToken.value = null
                }

            } catch (e: Exception) {
                Log.e("AuthViewModel", "Exception en exchange de token", e)
                _accessToken.value = null
            }
        }
    }

    private fun saveCodeVerifier(context: Context, verifier: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        prefs.edit().putString(codeVerifierKey, verifier).apply()
    }

    private fun loadCodeVerifier(context: Context): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return prefs.getString(codeVerifierKey, null)
    }

    private fun clearCodeVerifier(context: Context) {
        val prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        prefs.edit().remove(codeVerifierKey).apply()
    }

    fun getCodeVerifier(): String? = codeVerifier
}