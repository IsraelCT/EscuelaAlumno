package com.cg.escuelaalumno.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cg.escuelaalumno.model.LoginResponse
import com.cg.escuelaalumno.utils.LoginUiState
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import com.cg.escuelaalumno.viewModel.LoginViewModel
import com.cg.escuelaalumno.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (LoginResponse) -> Unit,
    onNavigateToRegister: (String) -> Unit
) {
    val state = viewModel.loginState
    var idAlumno by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is LoginUiState.Idle,
            is LoginUiState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    // Logo minimalista
                    Image(
                        painter = painterResource(id = R.drawable.cgd),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 16.dp),
                        contentScale = ContentScale.Fit
                    )

                    // Campo ID Alumno
                    OutlinedTextField(
                        value = idAlumno,
                        onValueChange = { idAlumno = it },
                        label = { Text("ID Alumno") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // Campo Contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // Botón principal con estilo M3
                    Button(
                        onClick = { viewModel.login(idAlumno, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Entrar",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    // Texto de registro minimalista
                    TextButton(
                        onClick = {
                            val idSeguro = if (idAlumno.isBlank()) "nuevo" else idAlumno
                            onNavigateToRegister(idSeguro)
                        }
                    ) {
                        Text(
                            "Registrar / Recuperar contraseña",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                    if (state is LoginUiState.Error) {
                        Text(
                            "Error: ${state.message}",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            is LoginUiState.Loading -> {
                // Pantalla de carga con Material 3
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Entrando...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            is LoginUiState.Success -> {
                LaunchedEffect(state.response) {
                    onLoginSuccess(state.response)
                }
            }
        }
    }
}