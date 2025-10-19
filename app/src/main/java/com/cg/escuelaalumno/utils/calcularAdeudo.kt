package com.cg.escuelaalumno.utils

import com.cg.escuelaalumno.model.AlumnoResponse
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime


import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
data class AdeudoResult(
    val adeudo: Int,
    val semanasFaltantes: List<Pair<Int, Int>> // (año, semana)
)

fun calcularAdeudo(
    alumno: AlumnoResponse,
    semanaActual: Int,
    fechaActual: LocalDateTime,
    obtenerFechaCorte: (Int, Int) -> LocalDateTime
): AdeudoResult {
    // 🔹 Tomamos semanas pagadas directamente del backend
    val semanasPagadas = alumno.recibos
        .filter { it.ano > 1900 && it.semana in 1..53 }
        .map { it.ano to it.semana }
        .toSet()

    if (semanasPagadas.isEmpty()) {
        return AdeudoResult(0, emptyList())
    }

    // 🔹 Determinar la última semana pagada
    val ultimaSemanaPagada = semanasPagadas.maxWith(compareBy({ it.first }, { it.second }))

    val semanasExigibles = mutableSetOf<Pair<Int, Int>>()

    // 🔹 Generamos exigibles desde la última semana pagada hasta la actual
    for (year in ultimaSemanaPagada.first..fechaActual.year) {
        val limiteSemana = if (year == fechaActual.year) semanaActual else 53

        // Si es el año de la última semana pagada, empezamos desde ahí
        val inicioSemana = if (year == ultimaSemanaPagada.first) ultimaSemanaPagada.second else 1

        for (semana in inicioSemana..limiteSemana) {
            val fechaCorte = obtenerFechaCorte(semana, year)
            if (fechaActual.isAfter(fechaCorte)) {
                semanasExigibles.add(year to semana)
            }
        }
    }

    // 🔹 Diferencia: exigibles - pagadas
    val semanasNoPagadas = (semanasExigibles - semanasPagadas)
        .sortedWith(compareBy({ it.first }, { it.second }))

    return AdeudoResult(
        adeudo = semanasNoPagadas.size,
        semanasFaltantes = semanasNoPagadas
    )
}