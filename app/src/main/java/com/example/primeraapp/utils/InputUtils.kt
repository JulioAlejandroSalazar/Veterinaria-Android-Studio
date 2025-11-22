package com.example.primeraapp.utils

object InputUtils {

    // Ejemplo: se reemplaza readLine por parámetro recibido
    fun leerTextoDesdeUI(input: String): String = input.trim()

    fun leerEnteroDesdeUI(input: String): Int? = input.toIntOrNull()

    fun leerDoubleDesdeUI(input: String): Double? = input.toDoubleOrNull()
}
