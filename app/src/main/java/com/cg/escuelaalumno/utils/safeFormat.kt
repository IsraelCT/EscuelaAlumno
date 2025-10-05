package com.cg.escuelaalumno.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun safeFormat(fecha: String?, salida: DateTimeFormatter): String {
    if (fecha.isNullOrBlank()) return "?"
    return try {
        // Caso ISO (yyyy-MM-dd)
        LocalDate.parse(fecha).format(salida)
    } catch (_: Exception) {
        try {
            // Caso dd/MM/yyyy
            LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(salida)
        } catch (_: Exception) {
            "?"
        }
    }
}

