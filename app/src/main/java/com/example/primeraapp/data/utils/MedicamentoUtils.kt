package com.example.primeraapp.data.utils

object MedicamentoUtils {
    fun calcularDosis(peso: Double, edad: Int): Double {
        return peso * if (edad < 5) 0.8 else 1.2
    }
}
