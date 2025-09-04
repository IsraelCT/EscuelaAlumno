package com.cg.escuelaalumno.data

import com.cg.escuelaalumno.model.AlumnoResponse
import com.cg.escuelaalumno.utils.Constans.Companion.ENDPOINT
import retrofit2.http.GET
import retrofit2.Response

interface EscuelaAlumnoApi {
@GET(ENDPOINT )
suspend fun  getAlumnoDatos(): Response<AlumnoResponse>
}