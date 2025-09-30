package com.cg.escuelaalumno.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.cg.escuelaalumno.viewModel.ReciboViewModel

@Composable
fun ReciboListScreen(viewModel: ReciboViewModel = hiltViewModel()) {
    val recibos = viewModel.recibosPaged.collectAsState().value.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(recibos.itemCount) { index ->
            recibos[index]?.let { recibo ->
                ReciboCard(recibo) {
                    // Acción al hacer clic
                }
            }
        }

        when (recibos.loadState.append) {
            is LoadState.Loading -> item {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is LoadState.Error -> item {
                Text("Error al cargar más recibos", color = Color.Red)
            }
            else -> {}
        }
    }
}