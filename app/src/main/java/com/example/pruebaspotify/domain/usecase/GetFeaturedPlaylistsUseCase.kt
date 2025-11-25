package com.example.pruebaspotify.domain.usecase

import com.example.pruebaspotify.data.repository.MusicRepository
import com.example.pruebaspotify.domain.model.Playlist
import com.example.pruebaspotify.data.mappers.toDomain

class GetFeaturedPlaylistsUseCase(private val repository: MusicRepository) {
    suspend operator fun invoke(token: String): List<Playlist> {
        val response = repository.getFeaturedPlaylists(token)
        return if (response.isSuccessful) response.body()?.toDomain() ?: emptyList() else emptyList()
    }
}