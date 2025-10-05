package com.cg.escuelaalumno.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cg.escuelaalumno.model.AlumnoResponse
import com.cg.escuelaalumno.model.CalendarioModel
import com.cg.escuelaalumno.utils.safeFormat
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.LocalDate

@Composable
fun CalendarioContent(respuesta: AlumnoResponse) {
    val grupo = respuesta.grupo
    val materias = respuesta.materias
    val plan = respuesta.planEstudio
    val personal = respuesta.personal
    val calendario = respuesta.calendario




    // Relaciones
    val materiaPorId = materias.associateBy { it.idMateria }
    val docenteNombre = personal
        .firstOrNull { it.idPersonal == grupo.docente }
        ?.idNombre ?: "Docente no asignado"
    val docenteApellido = personal
        .firstOrNull { it.idPersonal == grupo.docente }
        ?.idApellido ?: "Docente no asignado"




    // Filtramos calendario por el grupo del alumno
    val calendarioDelGrupo = calendario.filter { it.grupo == grupo.idGrupo }

    // Formato de salida como en tu ejemplo: "01 de octubre 2025"
    val formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    //val formatoSalida = DateTimeFormatter.ofPattern("dd 'de' MMMM yyyy", Locale("es", "MX"))

    Column(modifier = Modifier) {
        // Encabezado institucional
        Text(
            text = "CALENDARIO DEL GRUPO ${grupo.clave}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        Text(text = "Docente: ${docenteNombre} ${docenteApellido}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Horario: ${grupo.horario}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Plan de estudio: ${plan.plansEstudio}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Fecha de inicio: ${safeFormat(grupo.fInicio, formatoSalida)}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        // Encabezado de tabla
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF))
                .padding(8.dp)
        ) {
            Text("Materia", modifier = Modifier.weight(2f), fontWeight = FontWeight.Bold)
            Text("Semana", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("F.Inicio", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("F.Final", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        }
        Divider()

        // Filas
        LazyColumn {
            items(calendarioDelGrupo) { item: CalendarioModel ->
                val materia = item.materia?.let { materiaPorId[it] }
                val nombreMateria = materia?.nombre ?: "Materia desconocida"
                val semanas = materia?.duracion?.toString() ?: "-"
                val inicio = safeFormat(item.fInicio, formatoSalida)
                val final = safeFormat(item.fFinal, formatoSalida)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(nombreMateria, modifier = Modifier.weight(2f))
                    Text(semanas, modifier = Modifier.weight(1f))
                    Text(inicio, modifier = Modifier.weight(1f))
                    Text(final, modifier = Modifier.weight(1f))
                }
                Divider()
            }
        }
    }
}