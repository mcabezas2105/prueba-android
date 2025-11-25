package com.example.pruebaspotify.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaspotify.domain.usecase.GetFeaturedPlaylistsUseCase
import com.example.pruebaspotify.domain.usecase.GetNewReleasesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNewReleases: GetNewReleasesUseCase,
    private val getFeaturedPlaylists: GetFeaturedPlaylistsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadData(token: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val albums = getNewReleases(token)
                val playlists = getFeaturedPlaylists(token)
                _uiState.value = HomeUiState(
                    isLoading = false,
                    albums = albums,
                    playlists = playlists
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}