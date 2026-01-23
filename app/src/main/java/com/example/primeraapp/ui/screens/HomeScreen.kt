package com.example.primeraapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import com.example.data.local.LocalStorageManager
import com.example.primeraapp.ui.components.ConsultaActivaView
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.viewmodel.AuthViewModel
import com.example.primeraapp.viewmodel.ConsultaViewModel
import com.example.primeraapp.viewmodel.MascotaViewModel
import com.airbnb.lottie.compose.*
import com.example.primeraapp.ui.components.BaseScreen
import com.example.primeraapp.ui.theme.AppBackground


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
    var mostrarConsultaActivaDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val localStorage = remember { LocalStorageManager(context) }
    var consultaActiva by remember { mutableStateOf<com.example.data.local.ConsultaActiva?>(null) }

    LaunchedEffect(mostrarConsultaActivaDialog) {
        if (mostrarConsultaActivaDialog) {
            consultaActiva = localStorage.obtenerConsultaActiva()
        }
    }

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
                        Icon(Icons.Default.Menu, contentDescription = null)
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

                        DropdownMenuItem(
                            text = { Text("Consulta activa") },
                            onClick = {
                                menuExpanded = false
                                mostrarConsultaActivaDialog = true
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

        BaseScreen {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {

                        /*                    item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.semantics {
                                    contentDescription =
                                        "Pantalla principal de la aplicación veterinaria"
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
                        }*/

                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Text("Mascotas", style = MaterialTheme.typography.titleLarge)
                                    Text("Registra y administra las mascotas de tus clientes")

                                    Button(
                                        onClick = {
                                            navController.navigate(
                                                AppScreen.RegistrarMascota.createRoute(-1)
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Registrar Mascota")
                                    }

                                    OutlinedButton(
                                        onClick = {
                                            navController.navigate(AppScreen.VerMascotas.route)
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Ver Mascotas")
                                    }
                                }
                            }
                        }

                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Text("Consultas", style = MaterialTheme.typography.titleLarge)
                                    Text("Control y seguimiento de consultas médicas")

                                    Button(
                                        onClick = {
                                            navController.navigate(
                                                AppScreen.RegistrarConsulta.createRoute(-1)
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Registrar Consulta")
                                    }

                                    OutlinedButton(
                                        onClick = {
                                            navController.navigate(AppScreen.VerConsultas.route)
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Ver Consultas")
                                    }
                                }
                            }
                        }

                        item { Spacer(modifier = Modifier.height(160.dp)) }
                    }
                }

                val composition by rememberLottieComposition(
                    LottieCompositionSpec.Asset("Cat_Movement.json")
                )

                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .height(140.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }

    if (mostrarConsultaActivaDialog) {
        Dialog(onDismissRequest = { mostrarConsultaActivaDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Consulta activa",
                            style = MaterialTheme.typography.titleLarge
                        )

                        consultaActiva?.let {
                            ConsultaActivaView(consulta = it)
                        } ?: Text("No hay una consulta activa")

                        Button(
                            onClick = { mostrarConsultaActivaDialog = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cerrar")
                        }
                    }
                }
            }
        }
    }
}
