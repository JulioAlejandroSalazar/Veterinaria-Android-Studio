package com.example.primeraapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.ui.state.DuenoUiState
import com.example.domain.model.Dueno
import com.example.domain.repository.DuenoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DuenoViewModel(
    private val duenoRepository: DuenoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DuenoUiState())
    val uiState: StateFlow<DuenoUiState> = _uiState.asStateFlow()

    init {
        observeDuenos()
    }

    private fun observeDuenos() {
        viewModelScope.launch {
            duenoRepository.getDuenosFlow().collect { list ->
                _uiState.update {
                    it.copy(
                        duenos = list,
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

    fun onTelefonoChange(value: String) {
        _uiState.update { it.copy(telefono = value) }
    }

    fun onCorreoChange(value: String) {
        _uiState.update { it.copy(correo = value) }
    }


    fun agregarDueno(dueno: Dueno) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                duenoRepository.add(dueno)

                _uiState.update {
                    it.copy(
                        successMessage = "Dueño agregado correctamente",
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

    fun eliminarDueno(nombre: String) {
        viewModelScope.launch {
            try {
                duenoRepository.delete(nombre)
                _uiState.update { it.copy(successMessage = "Dueño eliminado") }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    fun limpiarMensajes() {
        _uiState.update {
            it.copy(
                errorMessage = null,
                successMessage = null
            )
        }
    }
}
