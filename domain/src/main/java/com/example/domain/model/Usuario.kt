package com.example.domain.model

open class Usuario(
    val nombre: String,
    val telefono: String,
    var correo: String
) {
    operator fun component1() = nombre
    operator fun component2() = correo
    operator fun component3() = telefono

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Usuario) return false
        return nombre.equals(other.nombre, ignoreCase = true) &&
                correo.equals(other.correo, ignoreCase = true)
    }

    override fun hashCode(): Int {
        var result = nombre.lowercase().hashCode()
        result = 31 * result + correo.lowercase().hashCode()
        return result
    }

    override fun toString(): String {
        return "Usuario(nombre='$nombre', correo='$correo', telefono='$telefono')"
    }
}
