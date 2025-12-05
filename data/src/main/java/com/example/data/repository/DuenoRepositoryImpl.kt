package com.example.data.repository

import com.example.domain.model.Dueno
import com.example.domain.repository.DuenoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DuenoRepositoryImpl : DuenoRepository {

    private val duenos = mutableListOf<Dueno>()
    private val duenosFlow = MutableStateFlow<List<Dueno>>(emptyList())

    override fun getDuenosFlow(): Flow<List<Dueno>> =
        duenosFlow.asStateFlow()

    override suspend fun getAll(): List<Dueno> =
        duenos.toList()

    override suspend fun findByNombre(nombre: String): Dueno? =
        duenos.find { it.nombre.equals(nombre, ignoreCase = true) }

    override suspend fun add(dueno: Dueno) {
        duenos.add(dueno)
        emit()
    }

    override suspend fun update(dueno: Dueno) {
        val index = duenos.indexOfFirst { it.nombre == dueno.nombre }
        if (index != -1) {
            duenos[index] = dueno
            emit()
        }
    }

    override suspend fun delete(nombre: String) {
        val removed = duenos.removeIf { it.nombre == nombre }
        if (removed) emit()
    }

    private fun emit() {
        duenosFlow.value = duenos.toList()
    }
}
