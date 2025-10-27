package com.cg.escuelaalumno.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cg.escuelaalumno.view.CalendarioView
import com.cg.escuelaalumno.view.GrupoView
import com.cg.escuelaalumno.view.HomeDrawerView
import com.cg.escuelaalumno.view.LoginView

import com.cg.escuelaalumno.view.PasswordRegisterView

import com.cg.escuelaalumno.view.ReciboScreen
import com.cg.escuelaalumno.view.RecibosTablaView

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
            LoginView(
                onLoginSuccess = { response ->
                    navController.navigate(Pantalla.Home.crearRuta(response.idAlumno)) {
                        popUpTo(Pantalla.Login.ruta) { inclusive = true }
                    }
                },
                onNavigateToRegister = { idAlumno ->
                    navController.navigate(Pantalla.RegistroContrasena.crearRuta(idAlumno))
                }
            )
        }
        // Pantalla de Registro/Actualización de contraseña
        composable(
            route = Pantalla.RegistroContrasena.rutaConArgumento,
            arguments = listOf(navArgument("idAlumno") { type = NavType.StringType })
        ) { backStackEntry ->
            val idAlumno = backStackEntry.arguments?.getString("idAlumno") ?: ""
            PasswordRegisterView(
                idAlumno = idAlumno,
                onPasswordRegistered = {
                    navController.popBackStack() // Regresa al login tras registrar
                }
            )
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



        composable(
            route = "Recibo/{alumnoId}",
            arguments = listOf(navArgument("alumnoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val alumnoId = backStackEntry.arguments?.getInt("alumnoId")
            if (alumnoId != null) {
                ReciboScreen(alumnoId = alumnoId)
            }
        }

        //Pantalla Calendario

        composable(Pantalla.Calendario.ruta) { backStackEntry ->
            val alumnoId = backStackEntry.arguments?.getString("")?.toIntOrNull()
            if (alumnoId != null) {
                CalendarioView(alumnoId)
            }
        }

    }
    }
