package com.cg.escuelaalumno.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cg.escuelaalumno.model.AlumnoResponse
import com.cg.escuelaalumno.utils.calcularAdeudo
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import com.cg.escuelaalumno.viewModel.ReciboViewModel
import java.time.LocalDateTime
import java.time.temporal.WeekFields
import kotlin.compareTo
/*
@Composable
fun RecibosTablaView(
    alumno: AlumnoResponse,
    semanaActual: Int,
    fechaActual: LocalDateTime,
    obtenerFechaCorte: (Int, Int) -> LocalDateTime
) {
    val adeudoResult = calcularAdeudo(
        alumno = alumno,
        semanaActual = semanaActual,
        fechaActual = fechaActual,
        obtenerFechaCorte = obtenerFechaCorte
    )

    val recibosOrdenados = alumno.recibos.sortedByDescending { it.fecha }
    val ultimoRecibo = recibosOrdenados.firstOrNull()

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔹 Encabezado con resumen de pagos
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                "Último pago: ${ultimoRecibo?.let { "${if (it.semana == 0) 37 else it.semana} / ${it.ano}" } ?: "-"}",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Semana actual: $semanaActual", style = MaterialTheme.typography.bodyMedium)
            Text(
                "Semanas pendientes: ${adeudoResult.adeudo}",
                style = MaterialTheme.typography.bodyMedium,
                color = if (adeudoResult.adeudo > 0) Color.Red else Color.Green
            )

            // 🔹 Mostrar detalle de semanas faltantes solo si es necesario
            if (adeudoResult.semanasFaltantes.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Faltan semanas: ${
                        adeudoResult.semanasFaltantes.joinToString { (anio, semana) -> "$semana/$anio" }
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 Encabezado de tabla
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(8.dp)
        ) {
            Text("Folio", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Semana", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Fecha/Hora", modifier = Modifier.weight(2f), fontWeight = FontWeight.Bold)
        }
        Divider()

        // 🔹 Lista de recibos
        LazyColumn {
            itemsIndexed(recibosOrdenados) { _, recibo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${recibo.folioPago}", modifier = Modifier.weight(1f))
                    Text("${if (recibo.semana == 0) 37 else recibo.semana}", modifier = Modifier.weight(1f))
                    Text("${recibo.fecha}", modifier = Modifier.weight(2f))
                }
                Divider()
            }
        }
    }
}




 */
/*
@Composable
fun RecibosTablaView(
    alumno: AlumnoResponse,
    semanaActual: Int,
    fechaActual: LocalDateTime,
    obtenerFechaCorte: (Int, Int) -> LocalDateTime
) {
    val adeudoResult = calcularAdeudo(
        alumno = alumno,
        semanaActual = semanaActual,
        fechaActual = fechaActual,
        obtenerFechaCorte = obtenerFechaCorte
    )

    // Ordenamos recibos una sola vez
    val recibosOrdenados = remember(alumno.recibos) {
        alumno.recibos.sortedByDescending { it.fecha }
    }
    val ultimoRecibo = recibosOrdenados.firstOrNull()

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔹 Encabezado con resumen de pagos
        Column(modifier = Modifier.padding(4.dp)) {
            val ultimoPagoTexto = ultimoRecibo?.let { recibo ->
                if (recibo.semana > 0) "${recibo.semana}/${recibo.ano}" else "?/${recibo.ano}"
            } ?: "-"

            Text("Último pago: $ultimoPagoTexto", style = MaterialTheme.typography.titleMedium)
            Text("Semana actual: $semanaActual", style = MaterialTheme.typography.bodyMedium)
            Text(
                "Semanas pendientes: ${adeudoResult.adeudo}",
                style = MaterialTheme.typography.bodyMedium,
                color = if (adeudoResult.adeudo > 0) Color.Red else Color.Green
            )

            if (adeudoResult.semanasFaltantes.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Faltan semanas: ${
                        adeudoResult.semanasFaltantes.joinToString { (anio, semana) -> "$semana" }
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 Encabezado de tabla
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(8.dp)
        ) {
            Text("Folio", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Semana", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Fecha/Hora", modifier = Modifier.weight(2f), fontWeight = FontWeight.Bold)
        }
        Divider()

        // 🔹 Lista de recibos
        LazyColumn {
            itemsIndexed(recibosOrdenados) { _, recibo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${recibo.folioPago}", modifier = Modifier.weight(1f))
                    Text("${recibo.semana}/${recibo.ano}", modifier = Modifier.weight(1f))
                    Text(recibo.fecha, modifier = Modifier.weight(2f))
                }
                Divider()
            }
        }
    }
}

 */
@Composable
fun RecibosTablaView(
    alumno: AlumnoResponse,
    semanaActual: Int,
    fechaActual: LocalDateTime,
    obtenerFechaCorte: (Int, Int) -> LocalDateTime
) {
    val adeudoResult = calcularAdeudo(
        alumno = alumno,
        semanaActual = semanaActual,
        fechaActual = fechaActual,
        obtenerFechaCorte = obtenerFechaCorte
    )

    // Ordenamos recibos una sola vez
    val recibosOrdenados = remember(alumno.recibos) { alumno.recibos.sortedByDescending { it.fecha } }
    val ultimoRecibo = recibosOrdenados.firstOrNull()

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔹 Encabezado con resumen de pagos
        Column(modifier = Modifier.padding(8.dp)) {
            val ultimoPagoTexto = ultimoRecibo?.let { recibo ->
                if (recibo.semana > 0) "${recibo.semana}/${recibo.ano}" else "?/${recibo.ano}"
            } ?: "-"

            Text(
                "Último pago: $ultimoPagoTexto",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                "Semana actual: $semanaActual",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                "Semanas pendientes: ${adeudoResult.adeudo}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = if (adeudoResult.adeudo > 0)
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.primary
            )

            if (adeudoResult.semanasFaltantes.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Faltan semanas: ${
                        adeudoResult.semanasFaltantes.joinToString { (_, semana) -> "$semana" }
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 Encabezado de tabla
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Text("Folio", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
            Text("Semana", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
            Text("Fecha/Hora", modifier = Modifier.weight(2f), style = MaterialTheme.typography.labelLarge)
        }
        Divider(color = MaterialTheme.colorScheme.outlineVariant)

        // 🔹 Lista de recibos
        LazyColumn {
            itemsIndexed(recibosOrdenados) { index, recibo ->
                val rowColor = if (index % 2 == 0)
                    MaterialTheme.colorScheme.surface
                else
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.15f)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(rowColor)
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${recibo.folioPago}", modifier = Modifier.weight(1f))
                    Text("${recibo.semana}", modifier = Modifier.weight(1f))
                    Text("${recibo.fecha} ${recibo.hora}", modifier = Modifier.weight(2f))
                }
                Divider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    }
}