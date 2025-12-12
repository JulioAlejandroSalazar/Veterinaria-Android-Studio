package com.example.data.repository

import com.example.domain.model.Mascota
import com.example.domain.repository.MascotaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MascotaRepositoryImpl : MascotaRepository {

    private val mascotas = mutableListOf<Mascota>()
    private val mascotasFlow = MutableStateFlow<List<Mascota>>(emptyList())

    override fun getMascotasFlow(): Flow<List<Mascota>> =
        mascotasFlow.asStateFlow()

    override suspend fun getAll(): List<Mascota> =
        mascotas.toList()

    override suspend fun findByNombre(nombre: String): List<Mascota> =
        mascotas.filter { it.nombre.equals(nombre, ignoreCase = true) }

    override suspend fun findByDueno(duenoNombre: String): List<Mascota> =
        mascotas.filter { it.dueno.nombre.equals(duenoNombre, ignoreCase = true) }

    override suspend fun add(mascota: Mascota) {
        mascotas.add(mascota)
        emit()
    }

    override suspend fun update(mascota: Mascota) {
        val index = mascotas.indexOfFirst {
            it.nombre == mascota.nombre &&
                    it.dueno.nombre == mascota.dueno.nombre
        }
        if (index != -1) {
            mascotas[index] = mascota
            emit()
        }
    }

    override suspend fun delete(nombre: String, duenoNombre: String) {
        val removed = mascotas.removeIf {
            it.nombre == nombre && it.dueno.nombre == duenoNombre
        }
        if (removed) emit()
    }

    private fun emit() {
        mascotasFlow.value = mascotas.toList()
    }

    fun getAllSync(): List<Mascota> = mascotas.toList()
}
