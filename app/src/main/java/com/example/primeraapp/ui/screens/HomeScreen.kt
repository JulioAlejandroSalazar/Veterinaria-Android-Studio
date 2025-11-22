package com.example.primeraapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.ui.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    totalMascotas: Int,
    totalConsultas: Int,
    ultimoDuenio: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bienvenido a la App Veterinaria") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // Mensaje de resumen
            Text("Total de mascotas registradas: $totalMascotas")
            Text("Total de consultas realizadas: $totalConsultas")
            Text("Último dueño registrado: $ultimoDuenio")

            Spacer(modifier = Modifier.height(24.dp))

            // Botones originales
            Button(
                onClick = { navController.navigate(AppScreen.RegistrarMascota.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar mascota")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(AppScreen.RegistrarConsulta.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar consulta")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(AppScreen.VerConsultas.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver consultas")
            }
        }
    }
}
