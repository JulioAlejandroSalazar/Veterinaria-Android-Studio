package com.example.primeraapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.local.ConsultaActiva

@Composable
fun ConsultaActivaView(consulta: ConsultaActiva) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Consulta activa",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Mascota: ${consulta.mascotaNombre}")
            Text("Especie: ${consulta.mascotaEspecie}")
            Text("Edad: ${consulta.mascotaEdad} años")
            Text("Dueño: ${consulta.duenoNombre}")
            Text("Motivo: ${consulta.motivoConsulta}")
        }
    }
}