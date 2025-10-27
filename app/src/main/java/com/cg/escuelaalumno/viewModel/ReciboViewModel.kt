package com.cg.escuelaalumno.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cg.escuelaalumno.data.paging.ReciboPager
import com.cg.escuelaalumno.model.AlumnoResponse
import com.cg.escuelaalumno.model.ReciboModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
@HiltViewModel
class ReciboViewModel @Inject constructor() : ViewModel() {

    private val _recibos = MutableStateFlow<List<ReciboModel>>(emptyList())
    val rebibos = _recibos.asStateFlow()

    fun cargarRecibosDesdeAlumno(alumno: AlumnoResponse) {
        _recibos.value = alumno.recibos
    }
}
