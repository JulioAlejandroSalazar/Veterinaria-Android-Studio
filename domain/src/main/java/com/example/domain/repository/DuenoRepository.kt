package com.example.domain.repository

import com.example.domain.model.Dueno
import kotlinx.coroutines.flow.Flow

interface DuenoRepository {

    fun getDuenosFlow(): Flow<List<Dueno>>

    suspend fun getAll(): List<Dueno>

    suspend fun findByNombre(nombre: String): Dueno?

    suspend fun add(dueno: Dueno)

    suspend fun update(dueno: Dueno)

    suspend fun delete(nombre: String)
}
