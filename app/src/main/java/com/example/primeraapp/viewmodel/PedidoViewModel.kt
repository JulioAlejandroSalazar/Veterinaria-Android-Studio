package com.example.primeraapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.ui.state.PedidoUiState
import com.example.domain.model.Pedido
import com.example.domain.repository.PedidoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PedidoViewModel(
    private val pedidoRepository: PedidoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PedidoUiState())
    val uiState: StateFlow<PedidoUiState> = _uiState.asStateFlow()

    init {
        observePedidos()
    }

    private fun observePedidos() {
        viewModelScope.launch {
            pedidoRepository.getPedidosFlow().collect { list ->
                _uiState.update {
                    it.copy(
                        pedidos = list,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun onNombreUsuarioChange(value: String) {
        _uiState.update { it.copy(nombreUsuario = value) }
    }

    fun onDireccionChange(value: String) {
        _uiState.update { it.copy(direccion = value) }
    }

    fun onProductosChange(value: List<String>) {
        _uiState.update { it.copy(productos = value) }
    }

    fun agregarPedido(p: Pedido) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                pedidoRepository.add(p)

                _uiState.update {
                    it.copy(
                        successMessage = "Pedido agregado correctamente",
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun eliminarPedido(nombreUsuario: String) {
        viewModelScope.launch {
            try {
                pedidoRepository.delete(nombreUsuario)
                _uiState.update { it.copy(successMessage = "Pedido eliminado") }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    fun limpiarMensajes() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}
