package com.example.primeraapp.data.app

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primeraapp.data.model.*
import com.example.primeraapp.data.navigation.AppScreen
import com.example.primeraapp.data.screens.*

@Composable
fun VeterinariaApp() {

    val navController = rememberNavController()

    val mascotas = remember { mutableStateListOf<Mascota>() }
    val consultas = remember { mutableStateListOf<Consulta>() }
    val veterinarios = remember {
        mutableStateListOf(
            Veterinario("Dr. Pérez", "Caninos", true),
            Veterinario("Dra. López", "Felinos", true),
            Veterinario("Dr. Soto", "Exóticos", true)
        )
    }

    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.route
    ) {

        composable(AppScreen.Home.route) {
            HomeScreen(navController)
        }

        composable(AppScreen.RegistrarMascota.route) {
            RegistrarMascotaScreen(navController, mascotas)
        }

        composable(AppScreen.RegistrarConsulta.route) {
            RegistrarConsultaScreen(navController, mascotas, veterinarios, consultas)
        }

        composable(AppScreen.VerConsultas.route) {
            VerConsultasScreen(navController, consultas)
        }
    }
}
