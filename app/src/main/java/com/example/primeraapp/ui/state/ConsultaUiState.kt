package com.example.primeraapp.ui.state

import com.example.domain.model.Consulta
import java.time.LocalDate
import java.time.LocalTime

data class ConsultaUiState(
    val consultas: List<Consulta> = emptyList(),

    // Formulario
    val mascotaNombre: String = "",
    val fecha: LocalDate? = null,
    val hora: LocalTime? = null,
    val motivo: String = "",
    val veterinario: String = "",
    val costoBase: Double = 0.0,

    // UI
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

