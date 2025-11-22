package com.example.primeraapp.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
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
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.ui.components.BotonVolverHome
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun RegistrarConsultaScreen(
    navController: NavHostController,
    mascotas: List<Mascota>,
    veterinarios: List<Veterinario>,
    consultas: MutableList<Consulta>
) {
    var seleccionadoMascotaIndex by remember { mutableStateOf(0) }
    var seleccionadoVetIndex by remember { mutableStateOf(0) }

    var motivo by remember { mutableStateOf("") }
    var costoBase by remember { mutableStateOf("") }
    var fechaTexto by remember { mutableStateOf(LocalDate.now().toString()) }
    var horaTexto by remember { mutableStateOf(LocalTime.now().toString().substring(0,5)) } // HH:mm

    var errorGeneral by remember { mutableStateOf("") }
    var errorMotivo by remember { mutableStateOf("") }
    var errorCosto by remember { mutableStateOf("") }
    var errorFecha by remember { mutableStateOf("") }

    if (mascotas.isEmpty()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Registrar consulta", style = MaterialTheme.typography.headlineSmall)
            Text("No hay mascotas registradas. Debes registrar al menos una mascota antes de crear una consulta.", color = Color.Red)
            BotonVolverHome(navController)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Registrar consulta", style = MaterialTheme.typography.headlineSmall)

        Text("Seleccionar mascota")
        DropdownMenuSelector(
            items = mascotas.map { it.nombre },
            selectedIndex = seleccionadoMascotaIndex,
            onSelect = { seleccionadoMascotaIndex = it }
        )

        Text("Seleccionar veterinario")
        if (veterinarios.isEmpty()) {
            Text("No hay veterinarios disponibles", color = Color.Red)
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
                // validaciones
                var valido = true
                errorGeneral = ""
                errorMotivo = ""
                errorCosto = ""
                errorFecha = ""

                if (motivo.isBlank()) {
                    errorMotivo = "Debes ingresar el motivo"
                    valido = false
                }

                val costoDouble = costoBase.toDoubleOrNull()
                if (costoBase.isBlank() || costoDouble == null || costoDouble < 0.0) {
                    errorCosto = "Costo inválido"
                    valido = false
                }

                val fechaParsed = try {
                    LocalDate.parse(fechaTexto)
                } catch (e: Exception) {
                    null
                }
                if (fechaParsed == null) {
                    errorFecha = "Formato de fecha inválido"
                    valido = false
                } else if (fechaParsed.isBefore(LocalDate.now())) {
                    errorFecha = "La fecha no puede ser anterior a hoy"
                    valido = false
                }

                if (veterinarios.isEmpty()) {
                    errorGeneral = "No hay veterinarios disponibles"
                    valido = false
                }

                if (!valido) return@Button

                val mascotaSeleccionada = mascotas[seleccionadoMascotaIndex]
                val veterinarioSeleccionado = veterinarios[seleccionadoVetIndex]

                try {
                    val consulta = Consulta(
                        mascota = mascotaSeleccionada,
                        veterinario = veterinarioSeleccionado,
                        fecha = fechaParsed!!,
                        hora = LocalTime.parse(horaTexto),
                        motivo = motivo,
                        costoBase = costoDouble!!
                    )

                    consultas.add(consulta)
                    Log.d("VetApp", "Consulta añadida. total consultas = ${consultas.size}")
                    navController.navigate(AppScreen.Home.route)

                } catch (e: Exception) {
                    errorGeneral = "Error al crear la consulta: ${e.message}"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        BotonVolverHome(navController)
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
                .menuAnchor() // NECESARIO para que funcione el dropdown
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelect(index)
                        expanded = false
                    }
                )
            }
        }
    }
}

