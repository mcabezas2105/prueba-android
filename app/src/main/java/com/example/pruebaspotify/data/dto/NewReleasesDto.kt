package com.example.pruebaspotify.data.dto
import com.google.gson.annotations.SerializedName

data class NewReleasesDto(
    val albums: Albums
)

data class Albums(
    val items: List<AlbumItem>
)

data class AlbumItem(
    val id: String,
    val name: String,
    val artists: List<Artist>,
    val images: List<Image>
)

data class Artist(
    val name: String
)

data class Image(
    val url: String
)