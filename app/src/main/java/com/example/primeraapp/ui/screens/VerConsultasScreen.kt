package com.example.primeraapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.ui.components.BotonVolverHome
import com.example.primeraapp.ui.components.ProgressOverlay
import com.example.primeraapp.viewmodel.ConsultaViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerConsultasScreen(
    navController: NavHostController,
    consultaViewModel: ConsultaViewModel
) {

    val uiState by consultaViewModel.uiState.collectAsState()
    val consultas = uiState.consultas

    var isVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(800)
        isLoading = false
        isVisible = true
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Consultas Registradas") }) }
    ) { innerPadding ->

        Box(Modifier.fillMaxSize()) {

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {

                    LazyColumn {
                        items(consultas) { consulta ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Mascota: ${consulta.mascota.nombre}")
                                    Text("Dueño: ${consulta.mascota.dueno.nombre}")
                                    Text("Veterinario: ${consulta.veterinario.nombre}")
                                    Text("Motivo: ${consulta.motivo}")
                                    Text("Fecha: ${consulta.fecha}")
                                    Text("Hora: ${consulta.hora}")
                                    Text("Costo final: ${consulta.calcularCostoFinal()}")
                                    Text("Estado: ${consulta.estado}")
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    BotonVolverHome(navController)
                }
            }

            ProgressOverlay(
                isLoading = isLoading,
                message = "Cargando consultas..."
            )
        }
    }
}

