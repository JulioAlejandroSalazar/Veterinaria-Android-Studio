package com.example.primeraapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.data.model.ResumenUI
import com.example.primeraapp.ui.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    resumen: ResumenUI
) {

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Veterinaria App") },
                actions = {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Registrar Mascota") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate(AppScreen.RegistrarMascota.route)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Registrar Consulta") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate(AppScreen.RegistrarConsulta.route)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Ver Consultas") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate(AppScreen.VerConsultas.route)
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenido a la Veterinaria",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(20.dp))

                // estadisticas
                Text("Mascotas registradas: ${resumen.totalMascotas}")
                Text("Consultas registradas: ${resumen.totalConsultas}")
                Text("Último dueño registrado: ${resumen.ultimoDueno ?: "Ninguno"}")

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { navController.navigate(AppScreen.RegistrarMascota.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar Mascota")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate(AppScreen.RegistrarConsulta.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar Consulta")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate(AppScreen.VerConsultas.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver Consultas")
                }
            }
        }
    }
}

