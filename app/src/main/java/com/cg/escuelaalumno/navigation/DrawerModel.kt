package com.cg.escuelaalumno.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class SeccionDrawer {
    PERFIL, CALIFICACIONES, MATERIAS, PLAN
}

data class ItemDrawer(
    val id: SeccionDrawer,
    val icono: ImageVector,
    val etiqueta: String
)

val itemsDrawer = listOf(
    ItemDrawer(SeccionDrawer.PERFIL, Icons.Default.Person, "Perfil"),
    ItemDrawer(SeccionDrawer.CALIFICACIONES, Icons.Default.Grade, "Calificaciones"),
    ItemDrawer(SeccionDrawer.MATERIAS, Icons.Default.Book, "Materias / Calendario"),
    ItemDrawer(SeccionDrawer.PLAN, Icons.Default.School, "Plan de estudio")
)
