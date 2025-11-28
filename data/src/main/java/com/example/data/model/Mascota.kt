package com.example.data.model

import java.time.LocalDate
import com.example.common.utils.DateUtils

data class Mascota(
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
