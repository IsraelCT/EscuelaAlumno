package com.cg.escuelaalumno.model

data class PlanEstudioModel(
    val idPlan: Int,
    val plansEstudio: String,
    val duracionPlan: Int,
    val especialidad: Int,
    val estatus: String,
    val usuario: String,
    val fechaCaptura: String
)