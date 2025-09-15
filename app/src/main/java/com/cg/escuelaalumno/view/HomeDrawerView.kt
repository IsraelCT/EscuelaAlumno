package com.cg.escuelaalumno.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cg.escuelaalumno.navigation.SeccionDrawer
import com.cg.escuelaalumno.navigation.itemsDrawer
import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDrawerView(alumnoId: Int) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()
    LaunchedEffect(alumnoId) { viewModel.fetchAlumnoPorId(alumnoId) }
    val alumnoState = viewModel.escuelaAlumno.collectAsStateWithLifecycle().value

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val seccionActiva = remember { mutableStateOf(SeccionDrawer.PERFIL) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(16.dp))
                itemsDrawer.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icono, contentDescription = item.etiqueta) },
                        label = { Text(item.etiqueta) },
                        selected = seccionActiva.value == item.id,
                        onClick = {
                            seccionActiva.value = item.id
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("Perfil del alumno") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            alumnoState?.let { alumno ->
                Column(modifier = Modifier.padding(padding).padding(24.dp)) {
                    when (seccionActiva.value) {
                        SeccionDrawer.PERFIL -> {
                            Text("👩‍🎓 ${alumno.alumno.nombre} ${alumno.alumno.apellidos}")
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Grupo: ${alumno.grupo.clave}")
                            Text("Horario: ${alumno.grupo.horario}")
                            Text("Docente: ${alumno.grupo.docente}")
                        }
                        SeccionDrawer.CALIFICACIONES -> {
                          CalificacionesView(alumnoId)
                        }
                        SeccionDrawer.MATERIAS -> {
                            alumno.materias.forEach {
                                Text("- ${it.nombre}: ${it.duracion} semanas")
                            }
                        }
                        SeccionDrawer.PLAN -> {
                            Text("Plan: ${alumno.planEstudio.plansEstudio}")
                            Text("Duración: ${alumno.planEstudio.duracionPlan} meses")
                            Text("Especialidad: ${alumno.planEstudio.especialidad}")
                        }
                    }
                }
            } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}