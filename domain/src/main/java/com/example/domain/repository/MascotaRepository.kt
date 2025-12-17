package com.example.domain.repository

import com.example.domain.model.Mascota
import kotlinx.coroutines.flow.Flow

interface MascotaRepository {

    fun getMascotasFlow(): Flow<List<Mascota>>
    suspend fun getAll(): List<Mascota>
    fun getAllSync(): List<Mascota>
    suspend fun getById(id: Long): Mascota?
    suspend fun findByNombre(nombre: String): List<Mascota>
    suspend fun findByDueno(duenoNombre: String): List<Mascota>
    suspend fun add(mascota: Mascota)
    suspend fun update(mascota: Mascota)
    suspend fun delete(id: Long)
}
