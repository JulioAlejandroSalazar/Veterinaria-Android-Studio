package com.example.domain.model

import java.time.LocalDate
import java.time.LocalTime

class Veterinario(
    nombre: String,
    val especialidad: String,
    var disponible: Boolean
) : Usuario(nombre, "N/A", "N/A") {

    private val consultasAsignadas = mutableListOf<Consulta>()

    fun disponible(hora: LocalTime, fecha: LocalDate): Boolean {
        if (!disponible) return false
        return consultasAsignadas.none { it.fecha == fecha && it.hora == hora }
    }

    fun asignarConsulta(consulta: Consulta) {
        consultasAsignadas.add(consulta)
    }

    fun listarConsultas(): List<Consulta> = consultasAsignadas.toList()
}
