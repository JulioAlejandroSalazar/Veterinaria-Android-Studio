package com.example.common.utils

object InputUtils {

    fun leerTextoDesdeUI(input: String): String = input.trim()

    fun leerEnteroDesdeUI(input: String): Int? = input.toIntOrNull()

    fun leerDoubleDesdeUI(input: String): Double? = input.toDoubleOrNull()
}
