package com.cg.escuelaalumno.model


data class MateriaModel(
    val idMateria: Int,
    val nombre: String,
    val duracion: Int,
    val estatus: String?        // puede ser null
)
