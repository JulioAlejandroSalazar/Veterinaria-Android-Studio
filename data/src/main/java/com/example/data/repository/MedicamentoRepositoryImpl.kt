package com.example.data.repository

import com.example.domain.model.Medicamento
import com.example.domain.repository.MedicamentoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MedicamentoRepositoryImpl : MedicamentoRepository {

    private val medicamentos = mutableListOf<Medicamento>()
    private val medicamentosFlow = MutableStateFlow<List<Medicamento>>(emptyList())

    override fun getMedicamentosFlow(): Flow<List<Medicamento>> =
        medicamentosFlow.asStateFlow()

    override suspend fun getAll(): List<Medicamento> =
        medicamentos.toList()

    override suspend fun findByNombre(nombre: String): List<Medicamento> =
        medicamentos.filter { it.nombre.equals(nombre, ignoreCase = true) }

    override suspend fun add(medicamento: Medicamento) {
        medicamentos.add(medicamento)
        emit()
    }

    override suspend fun update(medicamento: Medicamento) {
        val index = medicamentos.indexOfFirst { it.nombre == medicamento.nombre }
        if (index != -1) {
            medicamentos[index] = medicamento
            emit()
        }
    }

    override suspend fun delete(medicamento: Medicamento) {
        val removed = medicamentos.removeIf { it.nombre == medicamento.nombre }
        if (removed) emit()
    }

    private fun emit() {
        medicamentosFlow.value = medicamentos.toList()
    }
}
