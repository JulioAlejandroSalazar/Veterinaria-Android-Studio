package com.example.primeraapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.ui.screens.*
import com.example.primeraapp.viewmodel.MascotaViewModel
import com.example.primeraapp.viewmodel.ConsultaViewModel

@Composable
fun VeterinariaApp(
    mascotaViewModel: MascotaViewModel,
    consultaViewModel: ConsultaViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.route
    ) {

        composable(AppScreen.Home.route) {
            HomeScreen(
                navController = navController,
                mascotaViewModel = mascotaViewModel,
                consultaViewModel = consultaViewModel
            )
        }

        composable(AppScreen.RegistrarMascota.route) {
            RegistrarMascotaScreen(
                navController = navController,
                mascotaViewModel = mascotaViewModel
            )
        }

        composable(AppScreen.RegistrarConsulta.route) {
            RegistrarConsultaScreen(
                navController = navController,
                mascotaViewModel = mascotaViewModel,
                consultaViewModel = consultaViewModel
            )
        }

        composable(AppScreen.VerConsultas.route) {
            VerConsultasScreen(
                navController = navController,
                consultaViewModel = consultaViewModel
            )
        }
    }
}

