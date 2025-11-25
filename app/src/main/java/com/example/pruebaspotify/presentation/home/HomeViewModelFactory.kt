package com.example.pruebaspotify.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebaspotify.data.api.RetrofitSpotifyInstance
import com.example.pruebaspotify.data.repository.MusicRepository
import com.example.pruebaspotify.domain.usecase.GetFeaturedPlaylistsUseCase
import com.example.pruebaspotify.domain.usecase.GetNewReleasesUseCase

class HomeViewModelFactory(private val token: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val repo = MusicRepository(RetrofitSpotifyInstance.api) // usa SpotifyApi correcto
            val getNewReleases = GetNewReleasesUseCase(repo)
            val getFeaturedPlaylists = GetFeaturedPlaylistsUseCase(repo)
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(getNewReleases, getFeaturedPlaylists) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}