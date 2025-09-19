package com.cg.escuelaalumno.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cg.escuelaalumno.model.LoginRequest
import com.cg.escuelaalumno.model.LoginResponse
import com.cg.escuelaalumno.utils.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    var loginState by mutableStateOf<LoginUiState>(LoginUiState.Idle)
        private set

    fun login(idAlumno: String, password: String) {
        viewModelScope.launch {
            loginState = LoginUiState.Loading

            try {
                val response: LoginResponse = client.post("https://escuela-api-872866601915.us-central1.run.app/login") {
                    contentType(ContentType.Application.Json)
                    setBody(LoginRequest(idAlumno, password))
                }.body() // ← esta llave cierra correctamente el post

                loginState = LoginUiState.Success(response) // ← esta línea ya está dentro del try
            } catch (e: ClientRequestException) {
                loginState = LoginUiState.Error("Credenciales inválidas")
            } catch (e: Exception) {
                loginState = LoginUiState.Error("Error de red: ${e.message}")
            }
        }
    }
}