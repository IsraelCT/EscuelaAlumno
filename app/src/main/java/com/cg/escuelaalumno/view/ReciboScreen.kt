package com.cg.escuelaalumno.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import com.cg.escuelaalumno.viewModel.ReciboViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.cg.escuelaalumno.utils.obtenerAnioSemanaISO
import com.cg.escuelaalumno.utils.obtenerFechaCorte
import com.cg.escuelaalumno.utils.obtenerSemanaActualISO


import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.WeekFields
import java.util.Locale

/*
@Composable
fun ReciboScreen(
    alumnoId: Int,
    escuelaViewModel: EscuelaAlumnoVM = hiltViewModel(),
    reciboViewModel: ReciboViewModel = hiltViewModel()
) {
    val estadoAlumno by escuelaViewModel.escuelaAlumno.collectAsState()

    LaunchedEffect(alumnoId) {
        escuelaViewModel.fetchAlumnoPorId(alumnoId)
    }

    LaunchedEffect(estadoAlumno) {
        estadoAlumno?.let { alumno ->
            reciboViewModel.cargarRecibosDesdeAlumno(alumno)
        }
    }

    val fechaActual = LocalDateTime.now()
    val semanaActual = obtenerSemanaActualISO(fechaActual.toLocalDate())

    if (estadoAlumno != null) {
        RecibosTablaView(
            alumno = estadoAlumno!!,
            semanaActual = semanaActual,
            fechaActual = fechaActual,
            obtenerFechaCorte = ::obtenerFechaCorte
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}



 */

@Composable
fun ReciboScreen(
    alumnoId: Int,
    escuelaViewModel: EscuelaAlumnoVM = hiltViewModel(),
    reciboViewModel: ReciboViewModel = hiltViewModel()
) {
    val estadoAlumno by escuelaViewModel.escuelaAlumno.collectAsState()

    // 🔹 Cargar alumno al entrar
    LaunchedEffect(alumnoId) {
        escuelaViewModel.fetchAlumnoPorId(alumnoId)
    }

    // 🔹 Cargar recibos cuando ya tenemos alumno
    LaunchedEffect(estadoAlumno) {
        estadoAlumno?.let { alumno ->
            reciboViewModel.cargarRecibosDesdeAlumno(alumno)
        }
    }

    // 🔹 Fecha y semana/año ISO actuales
    val fechaActual = LocalDateTime.now()
    val semanaActual = obtenerSemanaActualISO(fechaActual.toLocalDate())
    val anioActual = obtenerAnioSemanaISO(fechaActual.toLocalDate())

    // 🔹 Renderizado condicional
    estadoAlumno?.let { alumno ->
        RecibosTablaView(
            alumno = alumno,
            semanaActual = semanaActual,
            fechaActual = fechaActual,
            obtenerFechaCorte = ::obtenerFechaCorte
        )
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}