package com.cg.escuelaalumno.model

import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

@Serializable
data class  ReciboModel (
    val idPago: Int,
    val idAlumno: Int,
    val nocred: String,
    val pago: Double,
    val fecha: String,
    val hora: String,
    val secretaria: String,
    val nombre: String,
    val concepto: String,
    val observacion: String,

    val folioPago: Int,
    val semana: Int,
    val ano: Int
)


