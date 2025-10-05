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
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale


@Composable
fun ReciboScreen(
    alumnoId: Int,
    escuelaViewModel: EscuelaAlumnoVM = hiltViewModel(),
    reciboViewModel: ReciboViewModel = hiltViewModel()
) {
    // Observar el flujo del alumno
    val estadoAlumno by escuelaViewModel.escuelaAlumno.collectAsState()

    // Lanzar la carga del alumno al entrar
    LaunchedEffect(alumnoId) {
        escuelaViewModel.fetchAlumnoPorId(alumnoId)
    }

    // Cuando ya tenemos datos del alumno, cargamos sus recibos en el ReciboViewModel
    LaunchedEffect(estadoAlumno) {
        estadoAlumno?.let { alumno ->
            reciboViewModel.cargarRecibosDesdeAlumno(alumno)
        }
    }

    // Renderizado condicional
    if (estadoAlumno != null) {
        RecibosTablaView(
            alumno = estadoAlumno!!,
            semanaActual = obtenerSemanaActual()
        )
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
fun obtenerSemanaActual(): Int {
    val hoy = LocalDate.now()
    val weekFields = WeekFields.of(Locale("es", "MX"))
    return hoy.get(weekFields.weekOfYear())
}
