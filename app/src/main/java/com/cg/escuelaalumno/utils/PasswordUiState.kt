package com.cg.escuelaalumno.utils

sealed class PasswordUiState {
    object Idle : PasswordUiState()
    object Loading : PasswordUiState()
    data class Success(val message: String) : PasswordUiState()
    data class Error(val message: String) : PasswordUiState()
}