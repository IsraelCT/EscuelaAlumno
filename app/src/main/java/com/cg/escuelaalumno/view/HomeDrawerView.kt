package com.cg.escuelaalumno.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.cg.escuelaalumno.di.ID_ALUMNO_KEY
import com.cg.escuelaalumno.di.USER_TOKEN_KEY
import com.cg.escuelaalumno.di.dataStore
import com.cg.escuelaalumno.navigation.Pantalla
import com.cg.escuelaalumno.navigation.SeccionDrawer
import com.cg.escuelaalumno.navigation.itemsDrawer
import com.cg.escuelaalumno.utils.obtenerFechaCorte

import com.cg.escuelaalumno.viewModel.EscuelaAlumnoVM
import com.cg.escuelaalumno.viewModel.ReciboViewModel
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.WeekFields
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDrawerView(
    alumnoId: Int,
    navController: NavController,
    context: Context = LocalContext.current
) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()
    val reciboVM: ReciboViewModel = hiltViewModel()

    // Cargar alumno
    LaunchedEffect(alumnoId) { viewModel.fetchAlumnoPorId(alumnoId) }
    val alumnoState = viewModel.escuelaAlumno.collectAsStateWithLifecycle().value

    // Cargar recibos cuando cambia el alumno
    LaunchedEffect(alumnoState) {
        alumnoState?.let { reciboVM.cargarRecibosDesdeAlumno(it) }
    }

    // 🔹 Semana actual ISO del calendario
    val fechaActual = LocalDateTime.now()
    val semanaActual = remember { LocalDate.now().get(WeekFields.ISO.weekOfYear()) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val seccionActiva = remember { mutableStateOf(SeccionDrawer.PERFIL) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(2.dp))

                // Items normales del Drawer
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

                Divider()

                // 👇 Item de Cerrar sesión
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión") },
                    label = { Text("Cerrar sesión") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            // Limpia credenciales (ejemplo con DataStore)
                            context.dataStore.edit { prefs ->
                                prefs.remove(USER_TOKEN_KEY)
                                prefs.remove(ID_ALUMNO_KEY)
                            }

                            drawerState.close()

                            // Navega al login y limpia el backstack
                            navController.navigate(Pantalla.Login.ruta) {
                                popUpTo(Pantalla.Home.ruta) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Computación del Golfo") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            alumnoState?.let { alumno ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(24.dp)
                ) {
                    when (seccionActiva.value) {
                        SeccionDrawer.PERFIL -> PerfilCard(
                            alumno = alumno,
                            semanaActual = semanaActual,
                            fechaActual = fechaActual,
                            obtenerFechaCorte = ::obtenerFechaCorte


                        )



                        SeccionDrawer.CALIFICACIONES -> CalificacionesView(alumnoId)

                        SeccionDrawer.RECIBO -> ReciboScreen(alumnoId = alumnoId)

                        SeccionDrawer.CALENDARIO -> CalendarioView(alumnoId)
                    }
                }
            } ?: Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDrawerView(
    alumnoId: Int,
    navController: NavController,
    context: Context = LocalContext.current
) {
    val viewModel: EscuelaAlumnoVM = hiltViewModel()
    val reciboVM: ReciboViewModel = hiltViewModel()

    // Cargar alumno
    LaunchedEffect(alumnoId) { viewModel.fetchAlumnoPorId(alumnoId) }
    val alumnoState = viewModel.escuelaAlumno.collectAsStateWithLifecycle().value

    // Cargar recibos cuando cambia el alumno
    LaunchedEffect(alumnoState) {
        alumnoState?.let { reciboVM.cargarRecibosDesdeAlumno(it) }
    }

    // 🔹 Semana actual ISO del calendario
    val fechaActual = LocalDateTime.now()
    val semanaActual = remember { LocalDate.now().get(WeekFields.ISO.weekOfYear()) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val seccionActiva = remember { mutableStateOf(SeccionDrawer.PERFIL) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surface,
                drawerContentColor = MaterialTheme.colorScheme.onSurface
            ) {
                // 🔹 Encabezado del Drawer
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Menú principal",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(Modifier.height(12.dp))

                // 🔹 Items del Drawer
                itemsDrawer.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icono, contentDescription = item.etiqueta) },
                        label = { Text(item.etiqueta) },
                        selected = seccionActiva.value == item.id,
                        onClick = {
                            seccionActiva.value = item.id
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // 🔹 Item de Cerrar sesión
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión") },
                    label = { Text("Cerrar sesión") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            // Limpia credenciales (ejemplo con DataStore)
                            context.dataStore.edit { prefs ->
                                prefs.remove(USER_TOKEN_KEY)
                                prefs.remove(ID_ALUMNO_KEY)
                            }
                            drawerState.close()
                            // Navega al login y limpia el backstack
                            navController.navigate(Pantalla.Login.ruta) {
                                popUpTo(Pantalla.Home.ruta) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Computación del Golfo",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { padding ->
            alumnoState?.let { alumno ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(24.dp)
                ) {
                    when (seccionActiva.value) {
                        SeccionDrawer.PERFIL -> PerfilCard(
                            alumno = alumno,
                            semanaActual = semanaActual,
                            fechaActual = fechaActual,
                            obtenerFechaCorte = ::obtenerFechaCorte
                        )
                        SeccionDrawer.CALIFICACIONES -> CalificacionesView(alumnoId)
                        SeccionDrawer.RECIBO -> ReciboScreen(alumnoId = alumnoId)
                        SeccionDrawer.CALENDARIO -> CalendarioView(alumnoId)
                    }
                }
            } ?: Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}