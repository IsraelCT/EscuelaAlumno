package com.cg.escuelaalumno.model

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    val idAlumno : String,
    val mensaje: String,
    val token: String
)