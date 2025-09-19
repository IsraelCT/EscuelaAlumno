package com.cg.escuelaalumno.view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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

@Composable
fun LoginView(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (LoginResponse) -> Unit
) {
    var idAlumno by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state = viewModel.loginState


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Logo centrado
            Image(
                painter = painterResource(id = R.drawable.cgd),
                contentDescription = "CG",
                modifier = Modifier
                    .size(220.dp)
                    .padding(bottom = 32.dp),
                contentScale = ContentScale.Fit
            )

            // Campo ID Alumno
            OutlinedTextField(
                value = idAlumno,
                onValueChange = { idAlumno = it },
                label = { Text("ID Alumno") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // Campo Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // Botón de login
            Button(
                onClick = { viewModel.login(idAlumno, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }

            Spacer(Modifier.height(16.dp))

            // Estado de login
            when (state) {
                is LoginUiState.Loading -> CircularProgressIndicator()
                is LoginUiState.Success -> {
                    LaunchedEffect(state.response) {
                        onLoginSuccess(state.response)
                    }
                }
                is LoginUiState.Error -> Text(
                    "Error: ${state.message}",
                    color = Color.Red
                )
                LoginUiState.Idle -> {}
            }
        }
    }
}