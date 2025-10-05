package com.cg.escuelaalumno.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cg.escuelaalumno.model.CalendarioModel
import com.cg.escuelaalumno.model.MateriaModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TablaCalendarioPorGrupo(
    calendario: List<CalendarioModel>,
    materias: List<MateriaModel>
) {
    val materiaPorId = materias.associateBy { it.idMateria }
    val formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy") // mismo formato para mostrar
   // val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("es", "MX"))
    // val formato = DateTimeFormatter.ofPattern("dd 'de' MMMM yyyy", Locale("es", "MX"))
    val calendarioPorGrupo = calendario.groupBy { it.grupo }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        calendarioPorGrupo.forEach { (grupoId, materiasCalendario) ->
            item {
                Text(
                    text = "Grupo ${grupoId ?: "Sin grupo"}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(materiasCalendario) { item: CalendarioModel ->
                val nombreMateria = item.materia?.let { materiaPorId[it]?.nombre } ?: "Materia desconocida"
                val inicio = item.fInicio?.let { LocalDate.parse(it, formatoEntrada).format(formatoSalida) } ?: "?"
                val final  = item.fFinal?.let { LocalDate.parse(it, formatoEntrada).format(formatoSalida) } ?: "?"

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Color(0xFFF9F9F9)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = nombreMateria, modifier = Modifier.weight(1f))
                    Text(text = "Inicio: $inicio", modifier = Modifier.weight(1f))
                    Text(text = "Final: $final", modifier = Modifier.weight(1f))
                }
            }

            item {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}