package com.example.primeraapp.ui.screens

import android.content.Intent
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
import com.example.primeraapp.MainActivity
import com.example.primeraapp.MenuActivity
import com.example.primeraapp.auth.AuthManager
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

    val mascotaState = mascotaViewModel.uiState.collectAsState()
    val consultaState = consultaViewModel.uiState.collectAsState()

    val mascotas = mascotaState.value.mascotas
    val consultas = consultaState.value.consultas

    val ultimoDueno = consultas.lastOrNull()?.mascota?.dueno?.nombre ?: "Ninguno"

    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

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
                                navController.navigate(AppScreen.RegistrarConsulta.createRoute(-1))
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Registrar Consulta") },
                            onClick = {
                                menuExpanded = false
                                navController.navigate(AppScreen.RegistrarConsulta.createRoute(-1))
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
                            text = { Text("Menú experimental") },
                            onClick = {
                                menuExpanded = false
                                navController.context.startActivity(
                                    Intent(navController.context, MenuActivity::class.java)
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Probar ContentProvider") },
                            onClick = {
                                menuExpanded = false
                                (navController.context as MainActivity).testProvider(
                                    navController.context
                                )
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

                Text("Mascotas registradas: ${mascotas.size}")
                Text("Consultas registradas: ${consultas.size}")
                Text("Último dueño registrado: $ultimoDueno")

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { navController.navigate(AppScreen.RegistrarMascota.createRoute(-1)) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Registrar Mascota") }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate(AppScreen.RegistrarConsulta.createRoute(-1)) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Registrar Consulta") }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate(AppScreen.VerConsultas.route) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Ver Consultas") }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate(AppScreen.VerMascotas.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver Mascotas")
                }


            }
        }
    }
}
