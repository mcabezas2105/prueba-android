package com.example.pruebaspotify.data.mappers



import com.example.pruebaspotify.data.dto.NewReleasesDto
import com.example.pruebaspotify.domain.model.Album

fun NewReleasesDto.toDomain(): List<Album> {
    return this.albums.items.map { item ->
        Album(
            id = item.id,
            name = item.name,
            artist = item.artists.joinToString(", ") { it.name },
            imageUrl = item.images.firstOrNull()?.url.orEmpty()
        )
    }
}