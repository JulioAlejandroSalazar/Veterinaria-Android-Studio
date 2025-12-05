package com.example.primeraapp.ui.state

import com.example.domain.model.Pedido

data class PedidoUiState(
    val pedidos: List<Pedido> = emptyList(),

    // Formulario
    val nombreUsuario: String = "",
    val direccion: String = "",
    val productos: List<String> = emptyList(),

    // UI
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
