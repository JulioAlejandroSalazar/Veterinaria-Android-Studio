package com.example.primeraapp.ui.navigation

sealed class AppScreen(val route: String) {
    object Home : AppScreen("home")
    object RegistrarMascota : AppScreen("registrarMascota")
    object RegistrarConsulta : AppScreen("registrarConsulta")
    object VerConsultas : AppScreen("verConsultas")
}