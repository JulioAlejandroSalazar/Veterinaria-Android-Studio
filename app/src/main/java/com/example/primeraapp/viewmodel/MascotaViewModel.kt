package com.example.primeraapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.ui.state.MascotaUiState
import com.example.domain.model.Mascota
import com.example.domain.repository.MascotaRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MascotaViewModel(
    private val mascotaRepository: MascotaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MascotaUiState())
    val uiState: StateFlow<MascotaUiState> = _uiState.asStateFlow()

    init {
        observeMascotas()
    }

    private fun observeMascotas() {
        viewModelScope.launch {
            mascotaRepository.getMascotasFlow().collect { list ->
                _uiState.update {
                    it.copy(
                        mascotas = list,
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

    fun onEspecieChange(value: String) {
        _uiState.update { it.copy(especie = value) }
    }

    fun onEdadChange(value: String) {
        _uiState.update { it.copy(edad = value) }
    }

    fun onDuenoNombreChange(value: String) {
        _uiState.update { it.copy(duenoNombre = value) }
    }

    fun agregarMascota(mascota: Mascota) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }

                mascotaRepository.add(mascota)

                _uiState.update { it.copy(
                    successMessage = "Mascota agregada correctamente",
                    isLoading = false
                ) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    errorMessage = e.message,
                    isLoading = false
                ) }
            }
        }
    }

    fun eliminarMascota(nombre: String, duenoNombre: String) {
        viewModelScope.launch {
            try {
                mascotaRepository.delete(nombre, duenoNombre)
                _uiState.update { it.copy(successMessage = "Mascota eliminada") }
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


class MascotaViewModelFactory(
    private val repo: MascotaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MascotaViewModel::class.java)) {
            return MascotaViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
