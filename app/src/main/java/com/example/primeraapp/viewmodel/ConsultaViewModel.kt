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
                _uiState.update { it.copy(
                    consultas = list,
                    isLoading = false,
                    errorMessage = null
                ) }
            }
        }
    }

    fun onMascotaNombreChange(nombre: String) {
        _uiState.update { it.copy(mascotaNombre = nombre) }
    }

    fun onVeterinarioChange(nombre: String) {
        _uiState.update { it.copy(veterinario = nombre) }
    }

    fun onFechaChange(fecha: LocalDate) {
        _uiState.update { it.copy(fecha = fecha) }
    }

    fun onHoraChange(hora: LocalTime) {
        _uiState.update { it.copy(hora = hora) }
    }

    fun onMotivoChange(motivo: String) {
        _uiState.update { it.copy(motivo = motivo) }
    }

    fun onCostoChange(costo: Double) {
        _uiState.update { it.copy(costoBase = costo) }
    }

    fun crearConsulta(
        mascota: Mascota,
        veterinario: Veterinario
    ): Consulta? {
        val fecha = uiState.value.fecha
        val hora = uiState.value.hora
        val motivo = uiState.value.motivo
        val costo = uiState.value.costoBase

        if (fecha == null || hora == null || motivo.isBlank()) return null

        return Consulta(
            mascota = mascota,
            veterinario = veterinario,
            fecha = fecha,
            hora = hora,
            motivo = motivo,
            costoBase = costo
        )
    }

    fun agregarConsulta(consulta: Consulta) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                consultaRepository.add(consulta)
                _uiState.update { it.copy(
                    successMessage = "Consulta registrada",
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

    fun eliminarConsulta(id: String, fecha: LocalDate, hora: LocalTime) {
        viewModelScope.launch {
            try {
                consultaRepository.delete(id, fecha, hora)
                _uiState.update { it.copy(successMessage = "Consulta eliminada") }
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


class ConsultaViewModelFactory(
    private val repo: ConsultaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConsultaViewModel::class.java)) {
            return ConsultaViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}