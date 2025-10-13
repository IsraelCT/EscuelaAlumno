package com.cg.escuelaalumno.utils

import com.cg.escuelaalumno.model.AlumnoResponse

data class AdeudoResult(
    val adeudo: Int,
    val semanasFaltantes: List<Int>
)

fun calcularAdeudo(
    alumno: AlumnoResponse,
    semanaActual: Int,
    semanaInicioCurso: Int,
    esSabadoHoraCorte: Boolean
): AdeudoResult {
    // Corrección simbólica: interpretar semana 0 como semana 37
    val semanasPagadas = alumno.recibos.map {
        if (it.semana == 0) 37 else it.semana
    }.toSet()

    // Semanas que deberían estar cubiertas desde el inicio del curso
    val semanasExigibles = if (esSabadoHoraCorte) {
        (semanaInicioCurso..semanaActual).toSet()
    } else {
        (semanaInicioCurso until semanaActual).toSet()
    }

    val semanasNoPagadas = (semanasExigibles - semanasPagadas).sorted()

    return AdeudoResult(
        adeudo = semanasNoPagadas.size,
        semanasFaltantes = semanasNoPagadas
    )
}