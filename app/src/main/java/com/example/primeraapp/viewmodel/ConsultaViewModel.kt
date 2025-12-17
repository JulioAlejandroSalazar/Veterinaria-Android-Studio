package com.example.primeraapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.primeraapp.ui.state.ConsultaUiState
import com.example.domain.model.Consulta
import com.example.domain.model.Mascota
import com.example.domain.model.Veterinario
import com.example.domain.repository.ConsultaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class ConsultaViewModel(
    private val consultaRepository: ConsultaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConsultaUiState())
    val uiState: StateFlow<ConsultaUiState> = _uiState.asStateFlow()

    init {
        observeConsultas()
    }

    private fun observeConsultas() {
        viewModelScope.launch {
            consultaRepository.getConsultasFlow().collect { list ->
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

    fun agregarConsulta(consulta: Consulta) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                consultaRepository.add(consulta)
                _uiState.update {
                    it.copy(
                        successMessage = "Consulta registrada",
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

    fun actualizarConsulta(consulta: Consulta) {
        viewModelScope.launch {
            try {
                consultaRepository.update(consulta)
                _uiState.update {
                    it.copy(successMessage = "Consulta actualizada")
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = e.message)
                }
            }
        }
    }

    fun eliminarConsulta(id: Long) {
        viewModelScope.launch {
            try {
                consultaRepository.delete(id)
                _uiState.update {
                    it.copy(successMessage = "Consulta eliminada")
                }
            } catch (e: Exception) {
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
                uiState.value.consultas
                    .filter { it.mascota.id == mascotaId }
                    .forEach {
                        consultaRepository.delete(it.id)
                    }

                _uiState.update {
                    it.copy(successMessage = "Consultas eliminadas")
                }
            } catch (e: Exception) {
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
