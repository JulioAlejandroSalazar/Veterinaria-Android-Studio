package com.example.data.remote.dto

data class VeterinarioDto(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val disponible: Boolean,
    val imagenUrl: String
)
