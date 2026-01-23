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
import com.example.domain.model.Dueno
import com.example.domain.model.Mascota
import com.example.primeraapp.ui.components.BaseScreen
import com.example.primeraapp.ui.components.BotonVolverHome
import com.example.primeraapp.ui.components.DropdownMenuSelector
import com.example.primeraapp.ui.components.ProgressOverlay
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.ui.theme.darkOutlinedTextFieldColors
import com.example.primeraapp.viewmodel.MascotaViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarMascotaScreen(
    navController: NavHostController,
    mascotaViewModel: MascotaViewModel,
    mascotaId: Long?
) {
    val mascotas = mascotaViewModel.uiState.collectAsState().value.mascotas

    val esEdicion = mascotaId != null

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

    LaunchedEffect(Unit) {
        isVisible = true
    }

    LaunchedEffect(mascotaId) {
        if (mascotaId != null) {
            val mascota = mascotaViewModel.getMascotaById(mascotaId)
            mascota?.let {
                nombre = it.nombre
                especie = it.especie
                edad = it.edad.toString()
                nombreDueno = it.dueno.nombre
                telefono = it.dueno.telefono
                correo = it.dueno.correo
                fechaVacuna = it.fechaUltimaVacuna.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (esEdicion) "Editar Mascota" else "Registrar Mascota")
                }
            )
        }
    ) { innerPadding ->
        BaseScreen {
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

                        Text("Total mascotas: ${mascotas.size}",
                            color = Color.White)

                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it; errorNombre = "" },
                            label = { Text("Nombre") },
                            isError = errorNombre.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            colors = darkOutlinedTextFieldColors()
                        )
                        if (errorNombre.isNotEmpty()) Text(errorNombre, color = Color.Red)

                        Text("Especie",
                            color = Color.White)
                        val especies = listOf("Gato", "Perro", "Hamster", "Pez")

                        DropdownMenuSelector(
                            items = especies,
                            selectedIndex = especies.indexOf(especie).coerceAtLeast(0),
                            onSelect = {
                                especie = especies[it]
                                errorEspecie = ""
                            }
                        )
                        if (errorEspecie.isNotEmpty()) Text(errorEspecie, color = Color.Red)

                        OutlinedTextField(
                            value = edad,
                            onValueChange = { edad = it; errorEdad = "" },
                            label = { Text("Edad") },
                            isError = errorEdad.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            colors = darkOutlinedTextFieldColors()
                        )
                        if (errorEdad.isNotEmpty()) Text(errorEdad, color = Color.Red)

                        OutlinedTextField(
                            value = nombreDueno,
                            onValueChange = { nombreDueno = it; errorDueno = "" },
                            label = { Text("Dueño") },
                            isError = errorDueno.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            colors = darkOutlinedTextFieldColors()
                        )
                        if (errorDueno.isNotEmpty()) Text(errorDueno, color = Color.Red)

                        OutlinedTextField(
                            value = telefono,
                            onValueChange = { telefono = it; errorTelefono = "" },
                            label = { Text("Teléfono") },
                            isError = errorTelefono.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            colors = darkOutlinedTextFieldColors()
                        )
                        if (errorTelefono.isNotEmpty()) Text(errorTelefono, color = Color.Red)

                        OutlinedTextField(
                            value = correo,
                            onValueChange = { correo = it; errorCorreo = "" },
                            label = { Text("Correo") },
                            isError = errorCorreo.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            colors = darkOutlinedTextFieldColors()
                        )
                        if (errorCorreo.isNotEmpty()) Text(errorCorreo, color = Color.Red)

                        OutlinedTextField(
                            value = fechaVacuna,
                            onValueChange = { fechaVacuna = it; errorFecha = "" },
                            label = { Text("Fecha vacuna (AAAA-MM-DD)") },
                            isError = errorFecha.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            colors = darkOutlinedTextFieldColors()
                        )
                        if (errorFecha.isNotEmpty()) Text(errorFecha, color = Color.Red)

                        Spacer(modifier = Modifier.weight(1f))

                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                            Button(
                                onClick = {
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

                                    val fechaParsed = try {
                                        LocalDate.parse(fechaVacuna)
                                    } catch (_: Exception) {
                                        null
                                    }

                                    if (fechaParsed == null) {
                                        errorFecha = "Fecha inválida"
                                        valido = false
                                    }

                                    if (!valido) return@Button

                                    isLoading = true
                                    scope.launch {
                                        val mascota = Mascota(
                                            id = mascotaId ?: System.currentTimeMillis(),
                                            nombre = nombre,
                                            especie = especie,
                                            edad = edadInt!!,
                                            dueno = Dueno(
                                                nombre = nombreDueno,
                                                telefono = telefono,
                                                correo = correo,
                                                password = ""
                                            ),
                                            fechaUltimaVacuna = fechaParsed!!
                                        )

                                        if (esEdicion) {
                                            mascotaViewModel.actualizarMascota(mascota)
                                        } else {
                                            mascotaViewModel.agregarMascota(mascota)
                                        }

                                        isLoading = false
                                        navController.navigate(AppScreen.Home.route)
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(if (esEdicion) "Guardar cambios" else "Registrar Mascota")
                            }

                            BotonVolverHome(navController)
                        }
                    }
                }

                ProgressOverlay(
                    isLoading = isLoading,
                    message = if (esEdicion) "Guardando cambios..." else "Registrando mascota..."
                )
            }
        }
    }
}
