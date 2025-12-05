package com.example.primeraapp.ui.state

import com.example.domain.model.Mascota

data class MascotaUiState(
    val mascotas: List<Mascota> = emptyList(),

    // Formulario
    val nombre: String = "",
    val especie: String = "",
    val edad: String = "",
    val duenoNombre: String = "",

    // UI
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
