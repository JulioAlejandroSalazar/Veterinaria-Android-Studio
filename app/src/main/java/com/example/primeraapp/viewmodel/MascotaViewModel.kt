package com.example.primeraapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.ui.state.MascotaUiState
import com.example.domain.model.Mascota
import com.example.domain.repository.MascotaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "MascotaViewModel"

class MascotaViewModel(
    private val mascotaRepository: MascotaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MascotaUiState())
    val uiState: StateFlow<MascotaUiState> = _uiState.asStateFlow()

    init {
        Log.d(TAG, "Inicializando MascotaViewModel")
        observeMascotas()
    }

    private fun observeMascotas() {
        viewModelScope.launch {
            Log.d(TAG, "Observando flujo de mascotas")
            mascotaRepository.getMascotasFlow().collect { list ->
                Log.d(TAG, "Mascotas recibidas: ${list.size}")
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

    fun agregarMascota(mascota: Mascota) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Iniciando registro de mascota")
                _uiState.update { it.copy(isLoading = true) }

                mascotaRepository.add(mascota)

                Log.i(TAG, "Mascota agregada correctamente")
                _uiState.update {
                    it.copy(
                        successMessage = "Mascota agregada correctamente",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al agregar mascota", e)
                _uiState.update {
                    it.copy(
                        errorMessage = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun actualizarMascota(mascota: Mascota) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Actualizando mascota ID=${mascota.id}")
                mascotaRepository.update(mascota)
                Log.i(TAG, "Mascota actualizada correctamente")
                _uiState.update {
                    it.copy(successMessage = "Mascota actualizada")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al actualizar mascota", e)
                _uiState.update {
                    it.copy(errorMessage = e.message)
                }
            }
        }
    }

    fun eliminarMascota(id: Long) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Eliminando mascota ID=$id")
                mascotaRepository.delete(id)
                Log.i(TAG, "Mascota eliminada correctamente")
                _uiState.update {
                    it.copy(successMessage = "Mascota eliminada")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al eliminar mascota", e)
                _uiState.update {
                    it.copy(errorMessage = e.message)
                }
            }
        }
    }

    suspend fun getMascotaById(id: Long): Mascota? =
        mascotaRepository.getById(id)
}

class MascotaViewModelFactory(
    private val repo: MascotaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MascotaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MascotaViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
