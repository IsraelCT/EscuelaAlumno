package com.cg.escuelaalumno.navigation

sealed class Pantalla(val ruta: String) {
    object Login : Pantalla("login")

    // Home recibe idAlumno como argumento
    object Home : Pantalla("home/{idAlumno}") {
        fun crearRuta(idAlumno: String) = "home/$idAlumno"
    }

    // Grupo también puede recibir idAlumno si lo necesitas
    object Grupo : Pantalla("grupo/{alumnoId}") {
        fun crearRuta(alumnoId: String) = "grupo/$alumnoId"
    }

    object Calificaciones : Pantalla("calificaciones")
    object Materias : Pantalla("materias")
    object Plan : Pantalla("plan")

    object Recibo : Pantalla("Recibo")
}