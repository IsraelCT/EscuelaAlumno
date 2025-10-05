package com.cg.escuelaalumno.model

import java.time.LocalDate

data class CalendarioModel(
    val idCalendario: Int?,
    val grupo: Int?,
    val materia: Int?,
    val fInicio: String?,
    val fFinal: String?,
    val notas: String,
    val fCaptura: String?,
    val usuario: String?
)
