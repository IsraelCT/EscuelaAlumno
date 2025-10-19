package com.cg.escuelaalumno.view

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.cg.escuelaalumno.model.AlumnoResponse
import com.cg.escuelaalumno.utils.calcularAdeudo
import java.time.LocalDateTime

/*
@Composable
fun PerfilCard(alumno: AlumnoResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth().height(300.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = Color(0xFF3F51B5),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${alumno.alumno.nombre} ${alumno.alumno.apellidos}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(" Grupo: ${alumno.grupo.clave}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Text(" Horario: ${alumno.grupo.horario}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Text(" Docente: ${alumno.grupo.docente}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

 */
@Composable
fun PerfilCard(
    alumno: AlumnoResponse,
    semanaActual: Int, // semana ISO actual
    fechaActual: LocalDateTime,
    obtenerFechaCorte: (Int, Int) -> LocalDateTime
) {
    val resultado = calcularAdeudo(
        alumno = alumno,
        semanaActual = semanaActual,
        fechaActual = fechaActual,
        obtenerFechaCorte = obtenerFechaCorte
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = Color(0xFF3F51B5),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${alumno.alumno.nombre} ${alumno.alumno.apellidos}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Grupo: ${alumno.grupo.clave}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Horario: ${alumno.grupo.horario}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))

            val docente = alumno.personal.firstOrNull()
            val nombreDocente = listOfNotNull(docente?.idNombre, docente?.idApellido).joinToString(" ")

            Text(
                text = "Docente: ${if (nombreDocente.isNotBlank()) nombreDocente else "Sin asignar"}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Resumen de adeudo
            Text(
                "Semana $semanaActual/52   Adeudo: ${resultado.adeudo}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = if (resultado.adeudo > 0) Color.Red else Color(0xFF388E3C)
            )

            // 🔹 Mostrar detalle de semanas faltantes (semana/año)
            if (resultado.semanasFaltantes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Semanas faltantes: ${
                        resultado.semanasFaltantes.joinToString(", ") { (anio, semana) -> "$semana" }
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red
                )
            }
        }
    }
}


/*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PerfilCard(alumno: AlumnoResponse, semanaActual: Int) {
    val resultado = calcularAdeudo(alumno, semanaActual)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Encabezado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3F51B5))
                    .padding(16.dp)
            ) {
                Text(
                    text = "${alumno.alumno.nombre} ${alumno.alumno.apellidos}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Group, contentDescription = "Grupo", tint = Color(0xFF3F51B5))
                    Spacer(Modifier.width(8.dp))
                    Text("Grupo: ${alumno.grupo.clave}")
                }

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, contentDescription = "Horario", tint = Color(0xFF3F51B5))
                    Spacer(Modifier.width(8.dp))
                    Text("Horario: ${alumno.grupo.horario}")
                }

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.School, contentDescription = "Docente", tint = Color(0xFF3F51B5))
                    Spacer(Modifier.width(8.dp))
                    Text("Docente: ${alumno.grupo.docente}")
                }

                Spacer(Modifier.height(16.dp))

                // Estado de pagos
                Text(
                    "Semana $semanaActual/52   Adeudo: ${resultado.adeudo}",
                    fontWeight = FontWeight.Bold,
                    color = if (resultado.adeudo > 0) Color.Red else Color(0xFF388E3C)
                )

                if (resultado.semanasFaltantes.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)

                    ) {
                        resultado.semanasFaltantes.forEach { semana ->
                            androidx.compose.material3.AssistChip(
                                onClick = { /* opcional: detalle */ },
                                label = { Text("Semana $semana") },
                                // ✅ Esto está dentro de un Composable, así que no da error
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = Color(0xFFFFCDD2),
                                    labelColor = Color.Red
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

 */