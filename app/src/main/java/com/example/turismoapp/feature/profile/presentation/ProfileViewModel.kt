package com.example.turismoapp.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turismoapp.feature.profile.data.repository.ProfileRepository
import com.example.turismoapp.feature.profile.domain.model.ProfileModel
import com.example.turismoapp.feature.profile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    // Inyecta el caso de uso para obtener los datos.
    private val getProfile: GetProfileUseCase = GetProfileUseCase(ProfileRepository())
) : ViewModel() {

    // Define los posibles estados de la UI.
    sealed interface ProfileUiState {
        data object Init : ProfileUiState
        data object Loading : ProfileUiState
        data class Success(val profile: ProfileModel) : ProfileUiState
        data class Error(val message: String) : ProfileUiState
    }

    // Estado privado y público para la UI.
    private val _state = MutableStateFlow<ProfileUiState>(ProfileUiState.Init)
    val state: StateFlow<ProfileUiState> = _state.asStateFlow()

    fun showProfile() {
        viewModelScope.launch {
            // Se activa el estado de carga.
            _state.value = ProfileUiState.Loading

            // Llama al caso de uso para obtener los datos del perfil.
            val result = getProfile.invoke()

            // Maneja el resultado de la operación.
            _state.value = result.fold(
                onSuccess = { profile ->
                    ProfileUiState.Success(profile)
                },
                onFailure = { throwable ->
                    ProfileUiState.Error(throwable.message ?: "Error desconocido")
                }
            )
        }
    }
}