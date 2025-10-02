package com.cg.escuelaalumno.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonalModel(
    val idPersonal: Int,
    val idNombre: String?
)
