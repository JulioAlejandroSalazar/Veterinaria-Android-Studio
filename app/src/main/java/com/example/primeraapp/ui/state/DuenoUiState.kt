package com.example.primeraapp.ui.state

import com.example.domain.model.Dueno

data class DuenoUiState(
    val duenos: List<Dueno> = emptyList(),

    // Formulario
    val nombre: String = "",
    val telefono: String = "",
    val correo: String = "",

    // UI
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
