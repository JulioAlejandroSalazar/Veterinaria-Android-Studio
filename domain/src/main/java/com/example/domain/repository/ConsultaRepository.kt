package com.example.domain.repository

import com.example.domain.model.Consulta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalTime

interface ConsultaRepository {

    fun getConsultasFlow(): Flow<List<Consulta>>

    suspend fun getAll(): List<Consulta>

    suspend fun getConsultasPorFecha(fecha: LocalDate): List<Consulta>

    suspend fun getConsulta(
        mascotaNombre: String,
        fecha: LocalDate,
        hora: LocalTime
    ): Consulta?

    suspend fun add(consulta: Consulta)

    suspend fun update(consulta: Consulta)

    suspend fun delete(
        mascotaNombre: String,
        fecha: LocalDate,
        hora: LocalTime
    )
}
