package com.cg.escuelaalumno.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cg.escuelaalumno.view.GrupoView
import com.cg.escuelaalumno.view.HomeDrawerView
import com.cg.escuelaalumno.view.LoginView

@Composable
fun AppNavHost(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Pantalla.Login.ruta,
        modifier = Modifier.padding(innerPadding)
    ) {

        // Pantalla de Login
        composable(Pantalla.Login.ruta) {
            LoginView { response ->
                // response contiene token y idAlumno
                navController.navigate(Pantalla.Home.crearRuta(response.idAlumno)) {
                    popUpTo(Pantalla.Login.ruta) { inclusive = true }
                }
            }
        }

        // Pantalla Home
        composable(Pantalla.Home.ruta) { backStackEntry ->
            val idAlumno = backStackEntry.arguments?.getString("idAlumno")?.toIntOrNull()
            if (idAlumno != null) {
                // 👇 ahora pasamos también navController y context
                HomeDrawerView(
                    alumnoId = idAlumno,
                    navController = navController,
                    context = context
                )
            }
        }

        // Pantalla Grupo
        composable(Pantalla.Grupo.ruta) { backStackEntry ->
            val alumnoId = backStackEntry.arguments?.getString("alumnoId")?.toIntOrNull()
            if (alumnoId != null) {
                GrupoView(alumnoId)
            }
        }
    }
}