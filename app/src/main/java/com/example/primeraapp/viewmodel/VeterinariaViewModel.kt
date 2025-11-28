package com.example.primeraapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.data.model.Consulta
import com.example.data.model.Mascota
import com.example.data.model.Veterinario
import com.example.data.model.*

class VeterinariaViewModel : ViewModel() {

    val mascotas = mutableStateListOf<Mascota>()
    val consultas = mutableStateListOf<Consulta>()
    val veterinarios = mutableStateListOf(
        Veterinario("Dr. Pérez", "Caninos", true),
        Veterinario("Dra. López", "Felinos", true),
        Veterinario("Dr. Soto", "Exóticos", true)
    )

    val resumenUI: ResumenUI
        get() = ResumenUI(
            totalMascotas = mascotas.size,
            totalConsultas = consultas.size,
            ultimoDueno = consultas.lastOrNull()?.mascota?.dueno?.nombre ?: "Ninguno"
        )
}
