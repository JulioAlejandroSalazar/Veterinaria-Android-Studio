package com.example.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Consulta(
    val id: Long,
    val mascota: Mascota,
    val veterinario: Veterinario,
    val fecha: LocalDate,
    val hora: LocalTime,
    val motivo: String,
    val costoBase: Double,
    var estado: EstadoConsulta = EstadoConsulta.PENDIENTE
) {
    fun calcularCostoFinal(): Double {
        val descuento = if (mascota.edad > 10) 0.1 else 0.0
        return costoBase * (1 - descuento)
    }

    fun generarResumen(): String {
        val costoFinal = calcularCostoFinal()
        return """
            Consulta registrada:
            Veterinario: ${veterinario.nombre}
            Mascota: ${mascota.nombre}
            Motivo: $motivo
            Costo final: $$costoFinal
            Estado: $estado
        """.trimIndent()
    }
}
