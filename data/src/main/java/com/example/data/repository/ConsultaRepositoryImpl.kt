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

    override fun getConsultasFlow(): Flow<List<Consulta>> =
        consultasFlow.asStateFlow()

    override suspend fun getAll(): List<Consulta> =
        consultas.toList()

    override suspend fun getConsultasPorFecha(fecha: LocalDate): List<Consulta> =
        consultas.filter { it.fecha == fecha }

    override suspend fun getConsulta(
        mascotaNombre: String,
        fecha: LocalDate,
        hora: LocalTime
    ): Consulta? =
        consultas.find {
            it.mascota.nombre == mascotaNombre &&
                    it.fecha == fecha &&
                    it.hora == hora
        }

    override suspend fun add(consulta: Consulta) {
        consultas.add(consulta)
        emit()
    }

    override suspend fun update(consulta: Consulta) {
        val index = consultas.indexOfFirst {
            it.mascota.nombre == consulta.mascota.nombre &&
                    it.fecha == consulta.fecha &&
                    it.hora == consulta.hora
        }
        if (index != -1) {
            consultas[index] = consulta
            emit()
        }
    }

    override suspend fun delete(mascotaNombre: String, fecha: LocalDate, hora: LocalTime) {
        val removed = consultas.removeIf {
            it.mascota.nombre == mascotaNombre &&
                    it.fecha == fecha &&
                    it.hora == hora
        }
        if (removed) emit()
    }

    private fun emit() {
        consultasFlow.value = consultas.toList()
    }
}
