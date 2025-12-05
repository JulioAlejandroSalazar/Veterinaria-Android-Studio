package com.example.data

import com.example.data.repository.ConsultaRepositoryImpl
import com.example.data.repository.MascotaRepositoryImpl
import com.example.domain.model.*
import com.example.domain.repository.ConsultaRepository
import com.example.domain.repository.MascotaRepository

object DataModule {
    val veterinarios = mutableListOf(
        Veterinario("Dr. Pérez", "Caninos", true),
        Veterinario("Dra. López", "Felinos", true),
        Veterinario("Dr. Soto", "Exóticos", true)
    )

    val mascotaRepository: MascotaRepository = MascotaRepositoryImpl()
    val consultaRepository: ConsultaRepository = ConsultaRepositoryImpl()
}

