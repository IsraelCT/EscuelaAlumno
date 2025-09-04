package com.cg.escuelaalumno.repository

import com.cg.escuelaalumno.data.EscuelaAlumnoApi
import com.cg.escuelaalumno.model.AlumnoResponse
import javax.inject.Inject

class EscuelaAlumnoRepository @Inject constructor(private  val escuelaAlumnoApi: EscuelaAlumnoApi  ){
    suspend fun getAlumnoDatosporId(id : Int): AlumnoResponse?{
        val response = escuelaAlumnoApi.getAlumnoDatosPorId(id = id )
        return if (response.isSuccessful){
            response.body()
        }
        else{
            println("Error al obtener datos del alumno: ${response.code()}")
            null

        }

    }

}