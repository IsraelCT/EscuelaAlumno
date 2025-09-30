package com.cg.escuelaalumno.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cg.escuelaalumno.model.ReciboModel

@Composable
fun ReciboCard(recibo: ReciboModel, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Folio: ${recibo.folioPago}", style = MaterialTheme.typography.titleMedium)
            Text("Alumno: ${recibo.nombre}", style = MaterialTheme.typography.bodyMedium)
            Text("Concepto: ${recibo.concepto}", style = MaterialTheme.typography.bodyMedium)
            Text("Monto: \$${recibo.pago}", style = MaterialTheme.typography.bodyMedium)
            Text("Fecha: ${recibo.fecha} ${recibo.hora}", style = MaterialTheme.typography.bodySmall)
            Text("Semana: ${recibo.semana} / Año: ${recibo.ano}", style = MaterialTheme.typography.bodySmall)
            if (recibo.observacion.isNotBlank()) {
                Text("Observación: ${recibo.observacion}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}