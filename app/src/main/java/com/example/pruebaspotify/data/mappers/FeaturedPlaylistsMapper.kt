package com.example.pruebaspotify.data.mappers

import com.example.pruebaspotify.data.dto.FeaturedPlaylistsDto
import com.example.pruebaspotify.domain.model.Playlist

fun FeaturedPlaylistsDto.toDomain(): List<Playlist> {
    return playlists.items.map { item ->
        Playlist(
            id = item.id,
            name = item.name,
            description = item.description ?: "",
            imageUrl = item.images.firstOrNull()?.url ?: ""
        )
    }
}