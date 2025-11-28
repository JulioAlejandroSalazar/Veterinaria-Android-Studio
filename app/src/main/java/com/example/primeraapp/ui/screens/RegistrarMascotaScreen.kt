package com.example.primeraapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.data.model.Dueño
import com.example.primeraapp.data.model.Mascota
import com.example.primeraapp.ui.components.BotonVolverHome
import com.example.primeraapp.ui.components.ProgressOverlay
import com.example.primeraapp.ui.navigation.AppScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarMascotaScreen(
    navController: NavHostController,
    mascotas: MutableList<Mascota>
) {
    var isVisible by remember { mutableStateOf(false) }

    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var nombreDueno by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var fechaVacuna by remember { mutableStateOf("") }

    var errorNombre by remember { mutableStateOf("") }
    var errorEspecie by remember { mutableStateOf("") }
    var errorEdad by remember { mutableStateOf("") }
    var errorDueno by remember { mutableStateOf("") }
    var errorTelefono by remember { mutableStateOf("") }
    var errorCorreo by remember { mutableStateOf("") }
    var errorFecha by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) { isVisible = true }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Registrar Mascota") }) }
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

                    Text("Total mascotas: ${mascotas.size}")

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it; errorNombre = "" },
                        label = { Text("Nombre") },
                        isError = errorNombre.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorNombre.isNotEmpty()) Text(errorNombre, color = Color.Red)

                    OutlinedTextField(
                        value = especie,
                        onValueChange = { especie = it; errorEspecie = "" },
                        label = { Text("Especie") },
                        isError = errorEspecie.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorEspecie.isNotEmpty()) Text(errorEspecie, color = Color.Red)

                    OutlinedTextField(
                        value = edad,
                        onValueChange = { edad = it; errorEdad = "" },
                        label = { Text("Edad") },
                        isError = errorEdad.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorEdad.isNotEmpty()) Text(errorEdad, color = Color.Red)

                    OutlinedTextField(
                        value = nombreDueno,
                        onValueChange = { nombreDueno = it; errorDueno = "" },
                        label = { Text("Dueño") },
                        isError = errorDueno.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorDueno.isNotEmpty()) Text(errorDueno, color = Color.Red)

                    OutlinedTextField(
                        value = telefono,
                        onValueChange = { telefono = it; errorTelefono = "" },
                        label = { Text("Teléfono") },
                        isError = errorTelefono.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorTelefono.isNotEmpty()) Text(errorTelefono, color = Color.Red)

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it; errorCorreo = "" },
                        label = { Text("Correo") },
                        isError = errorCorreo.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorCorreo.isNotEmpty()) Text(errorCorreo, color = Color.Red)

                    OutlinedTextField(
                        value = fechaVacuna,
                        onValueChange = { fechaVacuna = it; errorFecha = "" },
                        label = { Text("Fecha vacuna (AAAA-MM-DD)") },
                        isError = errorFecha.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorFecha.isNotEmpty()) Text(errorFecha, color = Color.Red)

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {
                            // VALIDACIONES
                            var valido = true

                            if (nombre.isBlank()) { errorNombre = "Campo requerido"; valido = false }
                            if (especie.isBlank()) { errorEspecie = "Campo requerido"; valido = false }

                            val edadInt = edad.toIntOrNull()
                            if (edadInt == null || edadInt < 0) {
                                errorEdad = "Edad inválida"
                                valido = false
                            }

                            if (nombreDueno.isBlank()) { errorDueno = "Campo requerido"; valido = false }
                            if (telefono.isBlank()) { errorTelefono = "Campo requerido"; valido = false }

                            if (!correo.contains("@") || !correo.contains(".")) {
                                errorCorreo = "Correo inválido"
                                valido = false
                            }

                            val fechaParsed: LocalDate? = try { LocalDate.parse(fechaVacuna) } catch (_: Exception) { null }
                            if (fechaParsed == null) {
                                errorFecha = "Fecha inválida"
                                valido = false
                            }

                            if (!valido) return@Button

                            isLoading = true
                            scope.launch {
                                delay(1500)

                                mascotas.add(
                                    Mascota(
                                        nombre = nombre,
                                        especie = especie,
                                        edad = edadInt!!,
                                        dueño = Dueño(nombreDueno, telefono, correo),
                                        fechaUltimaVacuna = fechaParsed!!
                                    )
                                )

                                isLoading = false
                                navController.navigate(AppScreen.Home.route)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Mascota")
                    }

                    Spacer(Modifier.height(16.dp))

                    BotonVolverHome(navController)
                }
            }

            ProgressOverlay(
                isLoading = isLoading,
                message = "Registrando mascota..."
            )
        }
    }
}

