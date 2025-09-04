package com.cg.escuelaalumno.model


data class GrupoModel(
    val idGrupo: Int,
    val clave: String,
    val fInicio: String,
    val horario: String,
    val docente: Int,
    val plan: Int,
    val activo: String,
    val sistema: String?,       // puede ser null
    val notas: String?,         // puede ser null
    val fCaptura: String,
    val usuario: String,
    val idPe: Int?              // puede ser null
)