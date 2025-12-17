package com.example.primeraapp.ui.navigation

sealed class AppScreen(val route: String) {
    object Home : AppScreen("home")

    object RegistrarMascota :
        AppScreen("registrarMascota/{mascotaId}") {
        fun createRoute(mascotaId: Long) =
            "registrarMascota/$mascotaId"
    }

    object RegistrarConsulta :
        AppScreen("registrarConsulta/{consultaId}") {
        fun createRoute(consultaId: Long) =
            "registrarConsulta/$consultaId"
    }

    object VerMascotas : AppScreen("verMascotas")
    object VerConsultas : AppScreen("verConsultas")
}
