package com.example.data.repository

import com.example.domain.model.Consulta
import com.example.domain.repository.ConsultaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.LocalTime

class ConsultaRepositoryImpl : ConsultaRepository {

    private val consultas = mutableListOf<Consulta>()
    private val consultasFlow = MutableStateFlow<List<Consulta>>(emptyList())
    private var nextId = 1L

    override fun getConsultasFlow(): Flow<List<Consulta>> =
        consultasFlow.asStateFlow()

    override suspend fun getAll(): List<Consulta> =
        consultas.toList()

    override suspend fun getById(id: Long): Consulta? =
        consultas.find { it.id == id }

    override suspend fun getConsultasPorFecha(fecha: LocalDate): List<Consulta> =
        consultas.filter { it.fecha == fecha }

    override suspend fun add(consulta: Consulta) {
        val consultaConId = consulta.copy(id = nextId++)
        consultas.add(consultaConId)
        emit()
    }

    override suspend fun update(consulta: Consulta) {
        val index = consultas.indexOfFirst { it.id == consulta.id }
        if (index != -1) {
            consultas[index] = consulta
            emit()
        }
    }

    override suspend fun delete(id: Long) {
        val removed = consultas.removeIf { it.id == id }
        if (removed) emit()
    }

    override fun getAllSync(): List<Consulta> =
        consultas.toList()


    private fun emit() {
        consultasFlow.value = consultas.toList()
    }
}

