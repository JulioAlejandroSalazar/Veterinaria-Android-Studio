package com.example.domain.model

import com.example.domain.annotations.Promocionable

@Promocionable
class Medicamento(
    val nombre: String,
    val dosificacion: String,
    val precio: Double,
    val esPromocionable: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Medicamento) return false
        return nombre.equals(other.nombre, ignoreCase = true) &&
                dosificacion.equals(other.dosificacion, ignoreCase = true)
    }

    override fun hashCode(): Int {
        var res = nombre.lowercase().hashCode()
        res = 31 * res + dosificacion.lowercase().hashCode()
        return res
    }

    override fun toString(): String {
        return "Medicamento(nombre='$nombre', dosificacion='$dosificacion', precio=$precio)"
    }
}
