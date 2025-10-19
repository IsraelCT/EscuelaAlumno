package com.cg.escuelaalumno.utils

import java.time.*
import java.time.temporal.WeekFields
/*
fun obtenerFechaCorte(semana: Int, year: Int): LocalDateTime {
    val weekFields = WeekFields.ISO
    val fechaSabado = LocalDate.of(year, 1, 4) // semana 1 ISO garantizada
        .with(weekFields.weekOfWeekBasedYear(), semana.toLong())
        .with(DayOfWeek.SATURDAY)

    return fechaSabado.atTime(8, 0)
}

fun obtenerSemanaActualISO(fecha: LocalDate): Int {
    val weekFields = WeekFields.ISO
    return fecha.get(weekFields.weekOfWeekBasedYear())
}


 */

fun obtenerFechaCorte(semana: Int, year: Int): LocalDateTime {
    val weekFields = WeekFields.ISO
    val fechaSabado = LocalDate.of(year, 1, 4) // el 4 de enero siempre cae en la semana 1 ISO
        .with(weekFields.weekOfWeekBasedYear(), semana.toLong())
        .with(DayOfWeek.SATURDAY)

    return fechaSabado.atTime(8, 0)
}

/**
 * Devuelve la semana ISO de una fecha dada.
 */
fun obtenerSemanaActualISO(fecha: LocalDate): Int {
    val weekFields = WeekFields.ISO
    return fecha.get(weekFields.weekOfWeekBasedYear())
}

/**
 * Devuelve el año ISO de una fecha dada.
 * Útil porque el 1 de enero puede pertenecer a la última semana del año anterior.
 */
fun obtenerAnioSemanaISO(fecha: LocalDate): Int {
    val weekFields = WeekFields.ISO
    return fecha.get(weekFields.weekBasedYear())
}



