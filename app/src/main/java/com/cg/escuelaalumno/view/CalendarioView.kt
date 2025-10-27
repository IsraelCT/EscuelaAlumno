package com.cg.escuelaalumno.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cg.escuelaalumno.model.CalendarioModel
import com.cg.escuelaalumno.model.MateriaModel
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
/*
@Composable
fun CalendarioView(alumnoId: Int) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()
    val estadoAlumno = viewModel.escuelaAlumno.collectAsState().value

    LaunchedEffect(alumnoId) {
        viewModel.fetchAlumnoPorId(alumnoId)
    }

    val respuesta = estadoAlumno
    if (respuesta == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    CalendarioContent(respuesta)
}

 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarioView(alumnoId: Int) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()
    val estadoAlumno = viewModel.escuelaAlumno.collectAsState().value

    LaunchedEffect(alumnoId) {
        viewModel.fetchAlumnoPorId(alumnoId)
    }

    val respuesta = estadoAlumno
    if (respuesta == null) {
        // Pantalla de carga con estilo Material 3
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        return
    }

    // Contenedor principal con padding y color de fondo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado con ícono y título
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = "Calendario",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Calendario",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contenido del calendario
        CalendarioContent(respuesta)
    }
}