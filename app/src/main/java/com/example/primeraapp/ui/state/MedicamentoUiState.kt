package com.example.primeraapp.ui.state

import com.example.domain.model.Medicamento

data class MedicamentoUiState(
    val medicamentos: List<Medicamento> = emptyList(),

    // Formulario
    val nombre: String = "",
    val descripcion: String = "",
    val stock: String = "",

    // UI
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
