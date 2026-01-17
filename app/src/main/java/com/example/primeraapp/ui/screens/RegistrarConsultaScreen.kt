package com.example.primeraapp.ui.screens

import androidx.compose.ui.platform.LocalContext
import com.example.data.local.LocalStorageManager
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
import com.example.data.DataModule
import com.example.domain.model.Consulta
import com.example.primeraapp.ui.components.BotonVolverHome
import com.example.primeraapp.ui.components.ProgressOverlay
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.viewmodel.ConsultaViewModel
import com.example.primeraapp.viewmodel.MascotaViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarConsultaScreen(
    navController: NavHostController,
    mascotaViewModel: MascotaViewModel,
    consultaViewModel: ConsultaViewModel,
    consultaId: Long?
) {
    val mascotas = mascotaViewModel.uiState.collectAsState().value.mascotas
    val veterinarios = DataModule.veterinarios
    val scope = rememberCoroutineScope()

    var seleccionadoMascotaIndex by remember { mutableStateOf(0) }
    var seleccionadoVetIndex by remember { mutableStateOf(0) }
    var motivo by remember { mutableStateOf("") }
    var costoBase by remember { mutableStateOf("") }
    var fechaTexto by remember { mutableStateOf(LocalDate.now().toString()) }
    var horaTexto by remember { mutableStateOf(LocalTime.now().toString().substring(0, 5)) }

    var isEditing by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val localStorage = remember { LocalStorageManager(context) }


    LaunchedEffect(consultaId) {
        if (consultaId != null) {
            isEditing = true
            val consulta = consultaViewModel.getConsultaById(consultaId)

            consulta?.let {
                seleccionadoMascotaIndex =
                    mascotas.indexOfFirst { m -> m.id == it.mascota.id }
                        .coerceAtLeast(0)

                seleccionadoVetIndex =
                    veterinarios.indexOfFirst { v -> v.nombre == it.veterinario.nombre }
                        .coerceAtLeast(0)

                motivo = it.motivo
                costoBase = it.costoBase.toString()
                fechaTexto = it.fecha.toString()
                horaTexto = it.hora.toString().substring(0, 5)
            }
        }
    }

    if (mascotas.isEmpty()) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text("No hay mascotas registradas", color = Color.Red)
            Spacer(Modifier.weight(1f))
            BotonVolverHome(navController)
        }
        return
    }

    Box(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                if (isEditing) "Editar consulta" else "Registrar consulta",
                style = MaterialTheme.typography.headlineSmall
            )

            Text("Mascota")
            DropdownMenuSelector(
                items = mascotas.map { it.nombre },
                selectedIndex = seleccionadoMascotaIndex,
                onSelect = { seleccionadoMascotaIndex = it }
            )

            Text("Veterinario")
            DropdownMenuSelector(
                items = veterinarios.map { it.nombre },
                selectedIndex = seleccionadoVetIndex,
                onSelect = { seleccionadoVetIndex = it }
            )

            Text("Motivo / Tipo de consulta")

            val tiposConsulta = listOf(
                "Se siente mal",
                "Vomita",
                "Se lesionó"
            )

            DropdownMenuSelector(
                items = tiposConsulta,
                selectedIndex = tiposConsulta.indexOf(motivo).coerceAtLeast(0),
                onSelect = { motivo = tiposConsulta[it] }
            )


            OutlinedTextField(
                value = fechaTexto,
                onValueChange = { fechaTexto = it },
                label = { Text("Fecha (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = horaTexto,
                onValueChange = { horaTexto = it },
                label = { Text("Hora (HH:mm)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = costoBase,
                onValueChange = { costoBase = it },
                label = { Text("Costo base") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    val mascota = mascotas[seleccionadoMascotaIndex]
                    val veterinario = veterinarios[seleccionadoVetIndex]

                    val consulta = Consulta(
                        id = consultaId ?: System.currentTimeMillis(),
                        mascota = mascota,
                        veterinario = veterinario,
                        fecha = LocalDate.parse(fechaTexto),
                        hora = LocalTime.parse(horaTexto),
                        motivo = motivo,
                        costoBase = costoBase.toDouble()
                    )

                    isLoading = true
                    scope.launch {
                        if (isEditing) {
                            consultaViewModel.actualizarConsulta(consulta)
                        } else {
                            consultaViewModel.agregarConsulta(consulta)
                        }

                        localStorage.guardarConsultaActiva(
                            mascotaNombre = mascota.nombre,
                            mascotaEspecie = mascota.especie,
                            mascotaEdad = mascota.edad,
                            duenoNombre = mascota.dueno.nombre,
                            motivoConsulta = consulta.motivo
                        )

                        isLoading = false
                        navController.navigate(AppScreen.Home.route)
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditing) "Actualizar" else "Registrar")
            }

            BotonVolverHome(navController)
        }

        ProgressOverlay(
            isLoading = isLoading,
            message = if (isEditing) "Actualizando consulta..." else "Registrando consulta..."
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
                        onSelect(index)
                        expanded = false
                    }
                )
            }
        }
    }
}

