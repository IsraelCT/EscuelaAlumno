package com.cg.escuelaalumno.view



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM


@Composable
fun GrupoView(alumnoId: Int) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()

    LaunchedEffect(alumnoId) {
        viewModel.fetchAlumnoPorId(alumnoId)
    }

    val alumnoState = viewModel.escuelaAlumno.collectAsStateWithLifecycle().value

    alumnoState?.let { alumno ->
        Column(modifier = Modifier.padding(24.dp)) {
            Text("👩‍🎓 ${alumno.alumno.nombre} ${alumno.alumno.apellidos}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Grupo: ${alumno.grupo.clave}")
            Text("Horario: ${alumno.grupo.horario}")
            Text("Docente: ${alumno.grupo.docente}")
        }
    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}