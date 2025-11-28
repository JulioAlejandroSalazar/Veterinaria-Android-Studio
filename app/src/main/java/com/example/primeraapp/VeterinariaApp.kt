package com.example.primeraapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primeraapp.viewmodel.VeterinariaViewModel
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.ui.screens.*

@Composable
fun VeterinariaApp() {

    val navController = rememberNavController()
    val vm: VeterinariaViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.route
    ) {

        composable(AppScreen.Home.route) {
            HomeScreen(
                navController = navController,
                resumen = vm.resumenUI
            )
        }

        composable(AppScreen.RegistrarMascota.route) {
            RegistrarMascotaScreen(
                navController = navController,
                mascotas = vm.mascotas
            )
        }

        composable(AppScreen.RegistrarConsulta.route) {
            RegistrarConsultaScreen(
                navController = navController,
                mascotas = vm.mascotas,
                veterinarios = vm.veterinarios,
                consultas = vm.consultas
            )
        }

        composable(AppScreen.VerConsultas.route) {
            VerConsultasScreen(
                navController = navController,
                consultas = vm.consultas
            )
        }
    }
}
