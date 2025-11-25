package com.example.pruebaspotify.data.dto

data class FeaturedPlaylistsDto(
    val playlists: Playlists
)

data class Playlists(
    val items: List<PlaylistItem>
)

data class PlaylistItem(
    val id: String,
    val name: String,
    val description: String?,
    val images: List<PlaylistImage>
)

data class PlaylistImage(
    val url: String
)