package com.cg.escuelaalumno.model

data class AlumnoResponse(
    val alumno: AlumnoModel,
    val grupo: GrupoModel,
    val materias: List<MateriaModel>,
    val planEstudio: PlanEstudioModel,
    val calificaciones: List<CalificacionesModel>
)