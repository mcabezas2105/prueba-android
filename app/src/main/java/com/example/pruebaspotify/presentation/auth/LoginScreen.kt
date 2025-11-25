package com.example.pruebaspotify.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.Image

import androidx.compose.material3.ButtonDefaults

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

import androidx.compose.material3.ExperimentalMaterial3Api


import com.example.pruebaspotify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(authViewModel: AuthViewModel = viewModel()) {

    val loginState by authViewModel.loginState.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        if (loginState) {
            // Pantalla de "abriendo Spotify"
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = Color(0xFF1DB954))
                Spacer(modifier = Modifier.height(16.dp))
                Text("Abriendo Spotify...", color = Color.White)
            }
        } else {
            // Pantalla simplificada
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // LOGO
                Image(
                    painter = painterResource(id = R.drawable.spotify_logo),
                    contentDescription = "Spotify Logo",
                    modifier = Modifier.size(160.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // BOTÓN PKCE → abre Spotify
                Button(
                    onClick = { authViewModel.startLogin(context) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    Text("Ir a Spotify", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.spotify_logo),
                contentDescription = "Spotify Logo",
                modifier = Modifier.size(160.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(50.dp)
            ) {
                Text("Ir a Spotify", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}