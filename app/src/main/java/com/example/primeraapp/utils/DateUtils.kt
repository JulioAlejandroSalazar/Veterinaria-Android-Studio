package com.example.primeraapp.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

object DateUtils {

    fun parseFecha(input: String): LocalDate? {
        return try {
            LocalDate.parse(input, formatter)
        } catch (_: Exception) {
            null
        }
    }

    fun parseHora(input: String): LocalTime? {
        return try {
            val t = LocalTime.parse(input)
            if (t in LocalTime.of(8, 0)..LocalTime.of(16, 0)) t else null
        } catch (_: Exception) {
            null
        }
    }

    fun calcularProximaVacunacion(ultima: LocalDate, especie: String): LocalDate {
        return if (especie.equals("perro", true)) ultima.plusYears(1)
        else if (especie.equals("gato", true)) ultima.plusMonths(6)
        else ultima.plusYears(2)
    }

    fun LocalDate.formatNice(): String = this.format(formatter)
    fun LocalTime.formatNice(): String = this.toString()
}
