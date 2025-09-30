package com.cg.escuelaalumno.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CalificacionesView(alumnoId: Int) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()
    LaunchedEffect(alumnoId) { viewModel.fetchAlumnoPorId(alumnoId) }
    val alumnoState = viewModel.escuelaAlumno.collectAsStateWithLifecycle().value

    alumnoState?.let { alumno ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text(" Calificaciones", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            alumno.calificaciones.forEach { calificacion ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(calificacion.nombreMateria, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Calificación: ${calificacion.calificacion}")
                        Text("Estatus: ${calificacion.estatusMateria}")
                    }
                }
            }
        }
    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}