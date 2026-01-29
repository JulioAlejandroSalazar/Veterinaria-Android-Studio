package com.example.primeraapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.ui.state.ConsultaUiState
import com.example.domain.model.Consulta
import com.example.domain.repository.ConsultaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.data.local.ConsultaActiva

private const val TAG = "ConsultaViewModel"

class ConsultaViewModel(
    private val consultaRepository: ConsultaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConsultaUiState())
    val uiState: StateFlow<ConsultaUiState> = _uiState.asStateFlow()

    var consultaActiva by mutableStateOf<ConsultaActiva?>(null)
        private set

    init {
        Log.d(TAG, "Inicializando ConsultaViewModel")
        observeConsultas()
    }

    private fun observeConsultas() {
        viewModelScope.launch {
            Log.d(TAG, "Observando flujo de consultas")
            consultaRepository.getConsultasFlow().collect { list ->
                Log.d(TAG, "Consultas recibidas: ${list.size}")
                _uiState.update {
                    it.copy(
                        consultas = list,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun mostrarConsultaActiva(consulta: ConsultaActiva) {
        Log.d(TAG, "Mostrando consulta activa")
        consultaActiva = consulta
    }

    fun limpiarConsultaActiva() {
        Log.d(TAG, "Limpiando consulta activa")
        consultaActiva = null
    }

    fun agregarConsulta(consulta: Consulta) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Iniciando registro de consulta")
                _uiState.update { it.copy(isLoading = true) }

                consultaRepository.add(consulta)

                Log.i(TAG, "Consulta registrada correctamente")
                _uiState.update {
                    it.copy(
                        successMessage = "Consulta registrada",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al registrar consulta", e)
                _uiState.update {
                    it.copy(
                        errorMessage = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun actualizarConsulta(consulta: Consulta) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Actualizando consulta ID=${consulta.id}")
                consultaRepository.update(consulta)
                Log.i(TAG, "Consulta actualizada correctamente")
                _uiState.update {
                    it.copy(successMessage = "Consulta actualizada")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al actualizar consulta", e)
                _uiState.update {
                    it.copy(errorMessage = e.message)
                }
            }
        }
    }

    fun eliminarConsulta(id: Long) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Eliminando consulta ID=$id")
                consultaRepository.delete(id)
                Log.i(TAG, "Consulta eliminada correctamente")
                _uiState.update {
                    it.copy(successMessage = "Consulta eliminada")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al eliminar consulta", e)
                _uiState.update {
                    it.copy(errorMessage = e.message)
                }
            }
        }
    }

    suspend fun getConsultaById(id: Long): Consulta? =
        consultaRepository.getById(id)

    fun eliminarConsultasDeMascota(mascotaId: Long) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Eliminando consultas de mascota ID=$mascotaId")
                uiState.value.consultas
                    .filter { it.mascota.id == mascotaId }
                    .forEach {
                        consultaRepository.delete(it.id)
                    }

                Log.i(TAG, "Consultas eliminadas correctamente")
                _uiState.update {
                    it.copy(successMessage = "Consultas eliminadas")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al eliminar consultas de mascota", e)
                _uiState.update {
                    it.copy(errorMessage = e.message)
                }
            }
        }
    }
}

class ConsultaViewModelFactory(
    private val repo: ConsultaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConsultaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ConsultaViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
