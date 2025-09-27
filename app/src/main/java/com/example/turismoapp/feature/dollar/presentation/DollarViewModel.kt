package com.example.turismoapp.feature.dollar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turismoapp.feature.dollar.domain.model.DollarModel
import com.example.turismoapp.feature.dollar.domain.usecase.FetchDollarUseCase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DollarViewModel(
    private val fetchDollarUseCase: FetchDollarUseCase,
) : ViewModel() {

    sealed class DollarUIState {
        object Loading : DollarUIState()
        class Error(val message: String) : DollarUIState()
        class Success(val data: DollarModel) : DollarUIState()
    }

    private val _uiState = MutableStateFlow<DollarUIState>(DollarUIState.Loading)
    val uiState: StateFlow<DollarUIState> = _uiState

    init {
        getDollarUpdates()
    }

    private fun getDollarUpdates() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchDollarUseCase.invoke().collect { latestData ->
                    _uiState.value = DollarUIState.Success(latestData)
                }
            } catch (e: Exception) {
                _uiState.value = DollarUIState.Error(e.message ?: "Unknown error")
            }
        }
    }

    suspend fun getToken(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
            } else {
                continuation.resume(task.result ?: "")
            }
        }
    }
}