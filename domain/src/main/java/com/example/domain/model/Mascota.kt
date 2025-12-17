package com.example.domain.model

import java.time.LocalDate
import com.example.domain.utils.DateUtils

data class Mascota(
    val id: Long,
    val nombre: String,
    val especie: String,
    val edad: Int,
    val dueno: Dueno,
    val fechaUltimaVacuna: LocalDate
) {
    fun mostrarInformacion(): String {
        val proximaVacuna = DateUtils.calcularProximaVacunacion(fechaUltimaVacuna, especie)
        return """
            Mascota: $nombre
            Especie: $especie
            Edad: $edad años
            Próxima vacunación: $proximaVacuna
        """.trimIndent()
    }
}
