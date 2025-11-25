package com.example.pruebaspotify.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSpotifyInstance {

    private const val BASE_URL = "https://api.spotify.com/v1/"

    val api: SpotifyApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyApi::class.java)
    }
}