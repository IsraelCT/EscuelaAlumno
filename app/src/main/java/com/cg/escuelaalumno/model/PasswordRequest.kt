package com.cg.escuelaalumno.model

import kotlinx.serialization.Serializable


@Serializable
data class PasswordRequest(val password: String)