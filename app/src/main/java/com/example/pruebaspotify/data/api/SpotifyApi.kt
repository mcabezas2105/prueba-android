package com.example.pruebaspotify.data.api

import com.example.pruebaspotify.data.dto.FeaturedPlaylistsDto
import com.example.pruebaspotify.data.dto.NewReleasesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SpotifyApi {
    @GET("browse/new-releases")
    suspend fun getNewReleases(@Header("Authorization") token: String): Response<NewReleasesDto>

    @GET("browse/featured-playlists")
    suspend fun getFeaturedPlaylists(@Header("Authorization") token: String): Response<FeaturedPlaylistsDto>
}