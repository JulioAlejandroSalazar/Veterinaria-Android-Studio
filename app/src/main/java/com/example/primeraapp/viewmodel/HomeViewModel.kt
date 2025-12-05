package com.example.primeraapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Mascota
import com.example.domain.model.Veterinario
import com.example.domain.repository.ConsultaRepository
import com.example.primeraapp.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val consultaRepository: ConsultaRepository,
    private val mascotas: List<Mascota>,
    private val veterinarios: List<Veterinario>
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        cargarResumen()
    }

    private fun cargarResumen() {
        viewModelScope.launch {
            val totalMascotas = mascotas.size
            val totalConsultas = consultaRepository.getAll().size
            val totalVeterinarios = veterinarios.size

            _uiState.value = HomeUiState(
                totalMascotas = totalMascotas,
                totalConsultas = totalConsultas,
                totalVeterinarios = totalVeterinarios
            )
        }
    }
}

