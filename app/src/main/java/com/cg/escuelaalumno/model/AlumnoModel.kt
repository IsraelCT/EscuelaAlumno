package com.cg.escuelaalumno.model

data class AlumnoModel(
    val idalumno: Int,
    val nombre: String,
    val apellidos: String,
    val grupo: Int,
    val numCredencial: String
)
