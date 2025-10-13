package com.cg.escuelaalumno.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cg.escuelaalumno.utils.PasswordUiState
import com.cg.escuelaalumno.viewModel.LoginViewModel

@Composable
fun PasswordRegisterView(
    viewModel: LoginViewModel = hiltViewModel(),
    idAlumno: String,
    onPasswordRegistered: () -> Unit
) {
    // Si llega "nuevo", el campo debe ser editable
    var alumnoId by remember { mutableStateOf(if (idAlumno == "nuevo") "" else idAlumno) }
    var password by remember { mutableStateOf("") }
    val state = viewModel.passwordState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo ID Alumno
        OutlinedTextField(
            value = alumnoId,
            onValueChange = { alumnoId = it },
            label = { Text("ID Alumno") },
            modifier = Modifier.fillMaxWidth(),
            enabled = idAlumno == "nuevo" // editable solo si llegó como "nuevo"
        )

        Spacer(Modifier.height(16.dp))

        // Campo de nueva contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Nueva contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        // Botón de registrar/actualizar
        Button(
            onClick = {
                if (alumnoId.isNotBlank() && password.isNotBlank()) {
                    viewModel.registrarPassword(alumnoId, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar / Actualizar contraseña")
        }

        Spacer(Modifier.height(16.dp))

        // Estado de la operación
        when (state) {
            is PasswordUiState.Loading -> CircularProgressIndicator()
            is PasswordUiState.Success -> {
                Text(state.message, color = Color.Green)
                LaunchedEffect(state) {
                    kotlinx.coroutines.delay(1500) // tiempo para leer el mensaje
                    onPasswordRegistered() // regresa al login
                }
            }
            is PasswordUiState.Error -> Text(state.message, color = Color.Red)
            PasswordUiState.Idle -> {}
        }
    }
}