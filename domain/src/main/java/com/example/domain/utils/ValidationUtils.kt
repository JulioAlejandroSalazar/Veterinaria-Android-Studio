package com.example.domain.utils

import java.util.regex.Pattern

object ValidationUtils {

    fun validarCorreo(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return Pattern.compile(regex).matcher(email).matches()
    }

    fun formatearTelefono(raw: String, defaultCountryCode: String = "56"): String {
        val digits = raw.filter { it.isDigit() }
        val d = when (digits.length) {
            8, 9 -> defaultCountryCode + digits
            10 -> digits
            else -> defaultCountryCode + digits.takeLast(9)
        }
        val country = d.substring(0, 2)
        val first = d.substring(2, 3)
        val part1 = d.substring(3, 7)
        val part2 = d.substring(7)
        return "+$country $first $part1 $part2"
    }

    fun validarCantidadEnRango(cantidad: Int): Boolean = cantidad in 1..100
}
