package com.example.domain.repository

import com.example.domain.model.Medicamento
import kotlinx.coroutines.flow.Flow

interface MedicamentoRepository {

    fun getMedicamentosFlow(): Flow<List<Medicamento>>

    suspend fun getAll(): List<Medicamento>

    suspend fun findByNombre(nombre: String): List<Medicamento>

    suspend fun add(medicamento: Medicamento)

    suspend fun update(medicamento: Medicamento)

    suspend fun delete(medicamento: Medicamento)
}
