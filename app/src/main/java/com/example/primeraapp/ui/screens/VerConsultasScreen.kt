package com.example.primeraapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.data.model.Consulta
import com.example.primeraapp.ui.components.BotonVolverHome

@Composable
fun VerConsultasScreen(
    navController: NavHostController,
    consultas: List<Consulta>
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text("Consultas Registradas", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(consultas) { consulta ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Mascota: ${consulta.mascota.nombre}")
                        Text("Dueño: ${consulta.mascota.dueño.nombre}")
                        Text("Veterinario: ${consulta.veterinario.nombre}")
                        Text("Motivo: ${consulta.motivo}")
                        Text("Fecha: ${consulta.fecha}")
                        Text("Hora: ${consulta.hora}")
                        Text("Costo final: ${consulta.calcularCostoFinal()}")
                        Text("Estado: ${consulta.estado}")
                    }
                }
            }
        }
        BotonVolverHome(navController)
    }
}
