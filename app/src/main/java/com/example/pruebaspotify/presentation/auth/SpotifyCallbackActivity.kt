package com.example.pruebaspotify.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.pruebaspotify.BuildConfig
import com.example.pruebaspotify.MainActivity

class SpotifyCallbackActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent?.data
        Log.d("Callback", "URI: $uri")

        if (uri != null && uri.toString().startsWith(BuildConfig.SPOTIFY_REDIRECT_URI)) {
            val code = uri.getQueryParameter("code")
            Log.d("Callback", "Código recibido: $code")

            if (code != null) {

                // Guardamos el código en el Intent para MainActivity
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("CODE", code)

                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)
            }
        }

        finish()
    }
}