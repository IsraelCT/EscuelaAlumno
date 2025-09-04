package com.cg.escuelaalumno.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cg.escuelaalumno.model.AlumnoResponse
import com.cg.escuelaalumno.repository.EscuelaAlumnoRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class EscuelaAlumnoVM @Inject constructor(
    private val escuelaAlumnoRepository: EscuelaAlumnoRepository
) : ViewModel() {

    private val _escuelaAlumno = MutableStateFlow<AlumnoResponse?>(null)
    val escuelaAlumno = _escuelaAlumno.asStateFlow()

    init {
        fetchEscuelaAlumno()
    }

    private fun fetchEscuelaAlumno() {
        viewModelScope.launch {
            val result = escuelaAlumnoRepository.getAlumnoDatos()
            _escuelaAlumno.value = result
        }
    }
}