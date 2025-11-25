package com.example.pruebaspotify.presentation.home

import com.example.pruebaspotify.domain.model.Album
import com.example.pruebaspotify.domain.model.Playlist

data class HomeUiState(
    val isLoading: Boolean = false,
    val albums: List<Album> = emptyList(),
    val playlists: List<Playlist> = emptyList(),
    val error: String? = null
)