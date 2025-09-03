package com.cg.escuelaalumno.di
import com.cg.escuelaalumno.data.EscuelaAlumnoApi
import com.cg.escuelaalumno.utils.Constans.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Aquí agregamos nuestras dependencias inyectadas

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesEscuelaAlumnoApi(retrofit: Retrofit) : EscuelaAlumnoApi{
    return retrofit.create(EscuelaAlumnoApi ::class.java)
}