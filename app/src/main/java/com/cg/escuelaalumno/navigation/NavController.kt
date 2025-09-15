package com.cg.escuelaalumno.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cg.escuelaalumno.view.GrupoView
import com.cg.escuelaalumno.view.HomeDrawerView


@Composable
fun NavController() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Pantalla.Home.ruta) {
        composable(Pantalla.Home.ruta) {
            HomeDrawerView(alumnoId = 1) // ← Aquí ya no necesitas pasar navController si usas Drawer
        }
        composable(Pantalla.Grupo.ruta) { backStackEntry ->
            val alumnoId = backStackEntry.arguments?.getString("alumnoId")?.toIntOrNull()
            if (alumnoId != null) {
                GrupoView(alumnoId)
            }
        }
        // Puedes agregar más rutas aquí si decides navegar desde el Drawer
    }
}