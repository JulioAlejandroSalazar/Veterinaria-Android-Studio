package com.example.primeraapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.VeterinarioRepositoryImpl
import com.example.domain.model.Veterinario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VeterinariosViewModel : ViewModel() {

    private val repository = VeterinarioRepositoryImpl()

    private val _veterinarios = MutableStateFlow<List<Veterinario>>(emptyList())
    val veterinarios: StateFlow<List<Veterinario>> = _veterinarios

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        cargarVeterinarios()
    }

    private fun cargarVeterinarios() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val lista = repository.getVeterinarios()
                _veterinarios.value = lista
            } catch (e: Exception) {
                Log.e("VeterinariosVM", "Error en ViewModel", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}