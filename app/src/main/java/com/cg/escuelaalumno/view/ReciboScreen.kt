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
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.WeekFields
import java.util.Locale


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

    fun esSabadoHoraCorte(): Boolean {
        val ahora = LocalDateTime.now()
        return ahora.dayOfWeek == DayOfWeek.SATURDAY && ahora.hour >= 8
    }

    val semanaActual = obtenerSemanaActual()
    val semanaInicioCurso = 37 // ← Aquí defines el inicio real del curso

    if (estadoAlumno != null) {
        RecibosTablaView(
            alumno = estadoAlumno!!,
            semanaActual = semanaActual,
            semanaInicioCurso = semanaInicioCurso,
            esSabadoHoraCorte = esSabadoHoraCorte()
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