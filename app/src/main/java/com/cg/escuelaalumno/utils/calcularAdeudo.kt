package com.cg.escuelaalumno.utils

import com.cg.escuelaalumno.model.AlumnoResponse

data class AdeudoResult(
    val adeudo: Int,
    val semanasFaltantes: List<Int>
)

fun calcularAdeudo(alumno: AlumnoResponse, semanaActual: Int): AdeudoResult {
    val semanasPagadas = alumno.recibos.map { it.semana }.toSet()
    val semanasTranscurridas = (1..semanaActual).toSet()
    val semanasNoPagadas = (semanasTranscurridas - semanasPagadas).sorted()

    return AdeudoResult(
        adeudo = semanasNoPagadas.size,
        semanasFaltantes = semanasNoPagadas
    )
}