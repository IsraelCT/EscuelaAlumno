package com.cg.escuelaalumno.model


data class CalificacionesModel(
    val idAlumno: Int,
    val idMateria: Int,
    val nombreMateria: String,
    val calificacion: Int,
    val estatusMateria: String
)
