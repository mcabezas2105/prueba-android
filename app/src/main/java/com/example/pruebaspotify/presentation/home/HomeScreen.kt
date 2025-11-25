package com.example.pruebaspotify.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pruebaspotify.domain.model.Album
import com.example.pruebaspotify.domain.model.Playlist
import com.example.pruebaspotify.presentation.home.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), token: String) {
    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.loadData(token)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            uiState.error != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error: ${uiState.error}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { homeViewModel.loadData(token) }) {
                        Text("Reintentar")
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.albums) { album -> AlbumCard(album) }
                    items(uiState.playlists) { playlist -> PlaylistCard(playlist) }
                }
            }
        }
    }
}
@Composable
fun AlbumCard(album: Album) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(album.imageUrl),
                contentDescription = album.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(album.name, style = MaterialTheme.typography.titleMedium)
                Text(album.artist, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun PlaylistCard(playlist: Playlist) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(playlist.imageUrl),
                contentDescription = playlist.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(playlist.name, style = MaterialTheme.typography.titleMedium)
                Text(playlist.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}