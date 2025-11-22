package com.example.primeraapp.data.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primeraapp.data.viewmodel.VeterinariaViewModel
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.ui.screens.*

@Composable
fun VeterinariaApp() {

    val navController = rememberNavController()

    // ViewModel GLOBAL para toda la app
    val vm: VeterinariaViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.route
    ) {

        composable(AppScreen.Home.route) {
            HomeScreen(
                navController = navController,
                totalMascotas = vm.totalMascotas,
                totalConsultas = vm.totalConsultas,
                ultimoDuenio = vm.ultimoDuenio
            )
        }

        composable(AppScreen.RegistrarMascota.route) {
            RegistrarMascotaScreen(navController, vm.mascotas)
        }

        composable(AppScreen.RegistrarConsulta.route) {
            RegistrarConsultaScreen(
                navController,
                vm.mascotas,
                vm.veterinarios,
                vm.consultas
            )
        }

        composable(AppScreen.VerConsultas.route) {
            VerConsultasScreen(navController, vm.consultas)
        }
    }
}
