package com.cg.escuelaalumno.data

import com.cg.escuelaalumno.model.AlumnoResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface EscuelaAlumnoApi {
    @GET("alumnos/datos/{id}")
    suspend fun getAlumnoDatosPorId(@Path("id") id: Int): Response<AlumnoResponse>
}