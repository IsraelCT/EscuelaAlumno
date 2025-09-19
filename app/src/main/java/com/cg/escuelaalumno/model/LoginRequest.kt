package com.cg.escuelaalumno.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val idAlumno: String,
    val password: String
)