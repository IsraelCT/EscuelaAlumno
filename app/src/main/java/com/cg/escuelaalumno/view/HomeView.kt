package com.cg.escuelaalumno.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeView(alumnoId: Int) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()

    LaunchedEffect(alumnoId) {
        viewModel.fetchAlumnoPorId(alumnoId)
    }

    val alumnoState = viewModel.escuelaAlumno.collectAsStateWithLifecycle().value

    val showGrupo = remember { mutableStateOf(false) }
    val showMaterias = remember { mutableStateOf(false) }
    val showCalificaciones = remember { mutableStateOf(false) }
    val showPlan = remember { mutableStateOf(false) }

    alumnoState?.let { alumno ->
        Column(modifier = Modifier.padding(32.dp)) {
            Text(
                text = " ${alumno.alumno.nombre} ${alumno.alumno.apellidos}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { showGrupo.value = !showGrupo.value }) {
                    Icon(Icons.Default.Groups, contentDescription = "Grupo")
                }
                IconButton(onClick = { showMaterias.value = !showMaterias.value }) {
                    Icon(Icons.Default.Book, contentDescription = "Materias")
                }
                IconButton(onClick = { showCalificaciones.value = !showCalificaciones.value }) {
                    Icon(Icons.Default.Grade, contentDescription = "Calificaciones")
                }
                IconButton(onClick = { showPlan.value = !showPlan.value }) {
                    Icon(Icons.Default.School, contentDescription = "Plan de estudios")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔍 Secciones colapsables
            if (showGrupo.value) {
                Text("Grupo: ${alumno.grupo.clave}")
                Text("Horario: ${alumno.grupo.horario}")
                Text("Docente: ${alumno.grupo.docente}")
            }

            if (showMaterias.value) {
                alumno.materias.forEach {
                    Text("- ${it.nombre} (${it.duracion} hrs)")
                }
            }

            if (showCalificaciones.value) {
                alumno.calificaciones.forEach {
                    Text("${it.nombreMateria}: ${it.calificacion} (${it.estatusMateria})")
                }
            }

            if (showPlan.value) {
                Text("Plan: ${alumno.planEstudio.plansEstudio}")
                Text("Duración: ${alumno.planEstudio.duracionPlan} meses")
                Text("Especialidad: ${alumno.planEstudio.especialidad}")
            }
        }
    } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}