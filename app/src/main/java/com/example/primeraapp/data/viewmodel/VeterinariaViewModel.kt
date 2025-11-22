package com.example.primeraapp.data.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.primeraapp.data.model.*

class VeterinariaViewModel : ViewModel() {

    // listas de estado global
    val mascotas = mutableStateListOf<Mascota>()
    val consultas = mutableStateListOf<Consulta>()

    val veterinarios = mutableStateListOf(
        Veterinario("Dr. Pérez", "Caninos", true),
        Veterinario("Dra. López", "Felinos", true),
        Veterinario("Dr. Soto", "Exóticos", true)
    )

    // variables derivadas
    val totalMascotas: Int
        get() = mascotas.size

    val totalConsultas: Int
        get() = consultas.size

    val ultimoDuenio: String
        get() = consultas.lastOrNull()?.mascota?.dueño?.nombre ?: "Ninguno"
}
