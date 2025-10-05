package com.cg.escuelaalumno.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cg.escuelaalumno.model.CalendarioModel
import com.cg.escuelaalumno.model.MateriaModel
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM

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