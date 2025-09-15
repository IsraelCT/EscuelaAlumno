package com.cg.escuelaalumno.navigation

sealed class Pantalla(val ruta: String) {
    object Home : Pantalla("home")
    object Grupo : Pantalla("grupo")
    object Calificaciones : Pantalla("calificaciones")
    object Materias : Pantalla("materias")
    object Plan : Pantalla("plan")
}