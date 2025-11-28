package com.example.primeraapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.data.model.Consulta
import com.example.primeraapp.data.model.Mascota
import com.example.primeraapp.data.model.Veterinario
import com.example.primeraapp.ui.components.BotonVolverHome
import com.example.primeraapp.ui.components.ProgressOverlay
import com.example.primeraapp.ui.navigation.AppScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarConsultaScreen(
    navController: NavHostController,
    mascotas: List<Mascota>,
    veterinarios: List<Veterinario>,
    consultas: MutableList<Consulta>
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

    var seleccionadoMascotaIndex by remember { mutableStateOf(0) }
    var seleccionadoVetIndex by remember { mutableStateOf(0) }

    var motivo by remember { mutableStateOf("") }
    var costoBase by remember { mutableStateOf("") }
    var fechaTexto by remember { mutableStateOf(LocalDate.now().toString()) }
    var horaTexto by remember { mutableStateOf(LocalTime.now().toString().substring(0,5)) }

    var errorGeneral by remember { mutableStateOf("") }
    var errorMotivo by remember { mutableStateOf("") }
    var errorCosto by remember { mutableStateOf("") }
    var errorFecha by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (mascotas.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Registrar consulta", style = MaterialTheme.typography.headlineSmall)
            Text("No hay mascotas registradas.", color = Color.Red)
            BotonVolverHome(navController)
        }
        return
    }

    Box(Modifier.fillMaxSize()) {

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text("Registrar consulta", style = MaterialTheme.typography.headlineSmall)
                Text("Total consultas: ${consultas.size}")

                Text("Seleccionar mascota")
                DropdownMenuSelector(
                    items = mascotas.map { it.nombre },
                    selectedIndex = seleccionadoMascotaIndex,
                    onSelect = { seleccionadoMascotaIndex = it }
                )

                Text("Seleccionar veterinario")
                if (veterinarios.isEmpty()) {
                    Text("No hay veterinarios", color = Color.Red)
                } else {
                    DropdownMenuSelector(
                        items = veterinarios.map { it.nombre },
                        selectedIndex = seleccionadoVetIndex,
                        onSelect = { seleccionadoVetIndex = it }
                    )
                }

                OutlinedTextField(
                    value = motivo,
                    onValueChange = { motivo = it; errorMotivo = "" },
                    label = { Text("Motivo") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorMotivo.isNotEmpty()) Text(errorMotivo, color = Color.Red)

                OutlinedTextField(
                    value = fechaTexto,
                    onValueChange = { fechaTexto = it; errorFecha = "" },
                    label = { Text("Fecha (AAAA-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorFecha.isNotEmpty()) Text(errorFecha, color = Color.Red)

                OutlinedTextField(
                    value = horaTexto,
                    onValueChange = { horaTexto = it },
                    label = { Text("Hora (HH:mm)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = costoBase,
                    onValueChange = { costoBase = it; errorCosto = "" },
                    label = { Text("Costo base") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                if (errorCosto.isNotEmpty()) Text(errorCosto, color = Color.Red)

                if (errorGeneral.isNotEmpty()) Text(errorGeneral, color = Color.Red)

                Button(
                    onClick = {
                        var valido = true
                        errorGeneral = ""
                        errorMotivo = ""
                        errorCosto = ""
                        errorFecha = ""

                        if (motivo.isBlank()) { errorMotivo = "Debes ingresar el motivo"; valido = false }

                        val costoDouble = costoBase.toDoubleOrNull()
                        if (costoDouble == null || costoDouble < 0.0) {
                            errorCosto = "Costo inválido"
                            valido = false
                        }

                        val fechaParsed = try {
                            LocalDate.parse(fechaTexto)
                        } catch (e: Exception) {
                            null
                        }
                        if (fechaParsed == null) {
                            errorFecha = "Fecha inválida"
                            valido = false
                        } else if (fechaParsed.isBefore(LocalDate.now())) {
                            errorFecha = "La fecha no puede ser anterior a hoy"
                            valido = false
                        }

                        if (veterinarios.isEmpty()) {
                            errorGeneral = "No hay veterinarios"
                            valido = false
                        }

                        if (!valido) return@Button

                        val mascota = mascotas[seleccionadoMascotaIndex]
                        val veterinario = veterinarios[seleccionadoVetIndex]

                        isLoading = true
                        scope.launch {
                            delay(1500)

                            consultas.add(
                                Consulta(
                                    mascota = mascota,
                                    veterinario = veterinario,
                                    fecha = fechaParsed!!,
                                    hora = LocalTime.parse(horaTexto),
                                    motivo = motivo,
                                    costoBase = costoDouble!!
                                )
                            )

                            isLoading = false
                            navController.navigate(AppScreen.Home.route)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar")
                }

                BotonVolverHome(navController)
            }
        }

        ProgressOverlay(
            isLoading = isLoading,
            message = "Registrando consulta..."
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuSelector(
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedText = if (items.isNotEmpty()) items[selectedIndex] else "n/a"

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text("Seleccionar") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelect(index)   // ← envía el índice
                        expanded = false
                    }
                )
            }
        }
    }
}

