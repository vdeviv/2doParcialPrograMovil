package com.example.turismoapp.feature.profile.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.turismoapp.R // Asegúrate de que este ID de recurso existe
import com.example.turismoapp.feature.profile.domain.model.ProfileModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel()
) {
    // Escucha los cambios en el estado del ViewModel.
    val state by profileViewModel.state.collectAsState()

    // Lanza la carga del perfil una sola vez.
    LaunchedEffect(Unit) {
        profileViewModel.showProfile()
    }

    // Maneja los diferentes estados de la UI.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (val st = state) {
            is ProfileViewModel.ProfileUiState.Error -> {
                // Muestra el mensaje de error si la carga falla.
                Text(
                    text = st.message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
            ProfileViewModel.ProfileUiState.Init,
            ProfileViewModel.ProfileUiState.Loading -> {
                // Muestra un indicador de carga.
                CircularProgressIndicator()
            }
            is ProfileViewModel.ProfileUiState.Success -> {
                // Muestra la información del perfil cuando los datos están listos.
                ProfileContent(st.profile)
            }
        }
    }
}

@Composable
fun ProfileContent(profile: ProfileModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = profile.pathUrl,
            contentDescription = "Foto de perfil de ${profile.name}",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape),
            contentScale = ContentScale.Crop,
            // Placeholder para cuando la imagen no carga.
            error = painterResource(id = R.drawable.ic_launcher_foreground)
        )
        Spacer(Modifier.height(16.dp))
        Text(profile.name, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(profile.email, style = MaterialTheme.typography.bodyMedium)
        Text(profile.cellphone, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        Text(
            profile.summary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )
    }
}