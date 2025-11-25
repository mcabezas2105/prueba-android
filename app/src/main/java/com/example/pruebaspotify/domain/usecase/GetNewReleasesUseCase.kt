package com.example.pruebaspotify.domain.usecase

import com.example.pruebaspotify.data.repository.MusicRepository
import com.example.pruebaspotify.domain.model.Album
import com.example.pruebaspotify.data.mappers.toDomain

class GetNewReleasesUseCase(private val repository: MusicRepository) {
    suspend operator fun invoke(token: String): List<Album> {
        val response = repository.getNewReleases(token)
        return if (response.isSuccessful) response.body()?.toDomain() ?: emptyList() else emptyList()
    }
}