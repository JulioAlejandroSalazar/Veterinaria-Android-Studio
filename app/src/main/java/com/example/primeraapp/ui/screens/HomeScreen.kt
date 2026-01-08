package com.example.primeraapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.viewmodel.AuthViewModel
import com.example.primeraapp.viewmodel.ConsultaViewModel
import com.example.primeraapp.viewmodel.MascotaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    mascotaViewModel: MascotaViewModel,
    consultaViewModel: ConsultaViewModel,
    authViewModel: AuthViewModel
) {

    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Veterinaria App",
                        modifier = Modifier.semantics {
                            contentDescription = "Título de la aplicación Veterinaria App"
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = { menuExpanded = true },
                        modifier = Modifier.semantics {
                            contentDescription = "Abrir menú de opciones"
                        }
                    ) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null
                        )
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {

                        DropdownMenuItem(
                            text = { Text("Registrar Mascota") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate(
                                    AppScreen.RegistrarMascota.createRoute(-1)
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Registrar Consulta") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate(
                                    AppScreen.RegistrarConsulta.createRoute(-1)
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Ver Consultas") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate(AppScreen.VerConsultas.route)
                            }
                        )

                        Divider()

                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Cerrar sesión",
                                    color = MaterialTheme.colorScheme.error
                                )
                            },
                            onClick = {
                                menuExpanded = false
                                authViewModel.logout()
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.semantics {
                            contentDescription = "Pantalla principal de la aplicación veterinaria"
                        }
                    ) {
                        Text(
                            text = "Bienvenido",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "Gestión de tu veterinaria",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics {
                                contentDescription = "Sección de gestión de mascotas"
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Mascotas",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text("Registra y administra las mascotas de tus clientes")

                            Button(
                                onClick = {
                                    navController.navigate(
                                        AppScreen.RegistrarMascota.createRoute(-1)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .semantics {
                                        contentDescription =
                                            "Botón para registrar una nueva mascota"
                                    }
                            ) {
                                Text("Registrar Mascota")
                            }

                            OutlinedButton(
                                onClick = {
                                    navController.navigate(AppScreen.VerMascotas.route)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .semantics {
                                        contentDescription =
                                            "Botón para ver la lista de mascotas registradas"
                                    }
                            ) {
                                Text("Ver Mascotas")
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics {
                                contentDescription = "Sección de gestión de consultas veterinarias"
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Consultas",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text("Control y seguimiento de consultas médicas")

                            Button(
                                onClick = {
                                    navController.navigate(
                                        AppScreen.RegistrarConsulta.createRoute(-1)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .semantics {
                                        contentDescription =
                                            "Botón para registrar una nueva consulta veterinaria"
                                    }
                            ) {
                                Text("Registrar Consulta")
                            }

                            OutlinedButton(
                                onClick = {
                                    navController.navigate(AppScreen.VerConsultas.route)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .semantics {
                                        contentDescription =
                                            "Botón para ver el historial de consultas"
                                    }
                            ) {
                                Text("Ver Consultas")
                            }
                        }
                    }
                }
            }
        }
    }
}