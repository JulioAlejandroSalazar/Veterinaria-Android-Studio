package com.example.primeraapp.data.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.data.model.Dueño
import com.example.primeraapp.data.model.Mascota
import com.example.primeraapp.data.navigation.AppScreen
import com.example.primeraapp.ui.components.BotonVolverHome
import java.time.LocalDate

@Composable
fun RegistrarMascotaScreen(
    navController: NavHostController,
    mascotas: MutableList<Mascota>
) {

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

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        Text("Registrar Mascota", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

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
            label = { Text("Especie (perro, gato...)") },
            isError = errorEspecie.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorEspecie.isNotEmpty()) Text(errorEspecie, color = Color.Red)

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it; errorEdad = "" },
            label = { Text("Edad (años)") },
            isError = errorEdad.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (errorEdad.isNotEmpty()) Text(errorEdad, color = Color.Red)

        OutlinedTextField(
            value = nombreDueno,
            onValueChange = { nombreDueno = it; errorDueno = "" },
            label = { Text("Nombre del dueño") },
            isError = errorDueno.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorDueno.isNotEmpty()) Text(errorDueno, color = Color.Red)

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it; errorTelefono = "" },
            label = { Text("Teléfono") },
            isError = errorTelefono.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        if (errorTelefono.isNotEmpty()) Text(errorTelefono, color = Color.Red)

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it; errorCorreo = "" },
            label = { Text("Correo electrónico") },
            isError = errorCorreo.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (errorCorreo.isNotEmpty()) Text(errorCorreo, color = Color.Red)

        OutlinedTextField(
            value = fechaVacuna,
            onValueChange = { fechaVacuna = it; errorFecha = "" },
            label = { Text("Fecha última vacuna (AAAA-MM-DD)") },
            isError = errorFecha.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorFecha.isNotEmpty()) Text(errorFecha, color = Color.Red)

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {

                var valido = true

                if (nombre.isBlank()) { errorNombre = "Debes ingresar un nombre"; valido = false }
                if (especie.isBlank()) { errorEspecie = "Debes ingresar una especie"; valido = false }

                val edadInt = edad.toIntOrNull()
                if (edadInt == null || edadInt < 0) {
                    errorEdad = "Edad inválida"
                    valido = false
                }

                if (nombreDueno.isBlank()) { errorDueno = "Debes ingresar un dueño"; valido = false }

                if (telefono.isBlank()) {
                    errorTelefono = "Debes ingresar un teléfono"
                    valido = false
                }

                if (!correo.contains("@") || !correo.contains(".")) {
                    errorCorreo = "Correo inválido"
                    valido = false
                }

                val fechaParsed = try { LocalDate.parse(fechaVacuna) } catch (_: Exception) { null }
                if (fechaParsed == null) {
                    errorFecha = "Formato de fecha inválido"
                    valido = false
                }

                if (valido) {

                    val nuevoDueno = Dueño(
                        nombre = nombreDueno,
                        telefono = telefono,
                        correo = correo
                    )

                    val nuevaMascota = Mascota(
                        nombre = nombre,
                        especie = especie,
                        edad = edadInt!!,
                        dueño = nuevoDueno,
                        fechaUltimaVacuna = fechaParsed!!
                    )

                    mascotas.add(nuevaMascota)

                    navController.navigate(AppScreen.Home.route)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar Mascota")
        }
        BotonVolverHome(navController)
    }
}
