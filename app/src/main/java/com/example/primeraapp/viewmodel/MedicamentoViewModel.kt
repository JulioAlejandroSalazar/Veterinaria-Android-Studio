package com.example.primeraapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.ui.state.MedicamentoUiState
import com.example.domain.model.Medicamento
import com.example.domain.repository.MedicamentoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MedicamentoViewModel(
    private val medicamentoRepository: MedicamentoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MedicamentoUiState())
    val uiState: StateFlow<MedicamentoUiState> = _uiState.asStateFlow()

    init {
        observeMedicamentos()
    }

    private fun observeMedicamentos() {
        viewModelScope.launch {
            medicamentoRepository.getMedicamentosFlow().collect { list ->
                _uiState.update {
                    it.copy(
                        medicamentos = list,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun onNombreChange(value: String) {
        _uiState.update { it.copy(nombre = value) }
    }

    fun onDescripcionChange(value: String) {
        _uiState.update { it.copy(descripcion = value) }
    }

    fun onStockChange(value: String) {
        _uiState.update { it.copy(stock = value) }
    }

    fun agregarMedicamento(m: Medicamento) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                medicamentoRepository.add(m)

                _uiState.update {
                    it.copy(
                        successMessage = "Medicamento agregado correctamente",
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

    fun eliminarMedicamento(medicamento: Medicamento) {
        viewModelScope.launch {
            try {
                medicamentoRepository.delete(medicamento)
                _uiState.update { it.copy(successMessage = "Medicamento eliminado") }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    fun limpiarMensajes() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}
