package com.example.primeraapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.ui.screens.*
import com.example.primeraapp.viewmodel.MascotaViewModel
import com.example.primeraapp.viewmodel.ConsultaViewModel
import com.example.primeraapp.viewmodel.AuthViewModel

@Composable
fun VeterinariaApp(
    mascotaViewModel: MascotaViewModel,
    consultaViewModel: ConsultaViewModel,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
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
                consultaViewModel = consultaViewModel,
                authViewModel = authViewModel
            )
        }

        composable(
            route = AppScreen.RegistrarMascota.route,
            arguments = listOf(
                navArgument("mascotaId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->

            val mascotaId =
                backStackEntry.arguments
                    ?.getLong("mascotaId")
                    ?.takeIf { it != -1L }

            RegistrarMascotaScreen(
                navController = navController,
                mascotaViewModel = mascotaViewModel,
                mascotaId = mascotaId
            )
        }

        composable(
            route = AppScreen.RegistrarConsulta.route,
            arguments = listOf(
                navArgument("consultaId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->

            val consultaId =
                backStackEntry.arguments
                    ?.getLong("consultaId")
                    ?.takeIf { it != -1L }

            RegistrarConsultaScreen(
                navController = navController,
                mascotaViewModel = mascotaViewModel,
                consultaViewModel = consultaViewModel,
                consultaId = consultaId
            )
        }

        composable(AppScreen.VerConsultas.route) {
            VerConsultasScreen(
                navController = navController,
                consultaViewModel = consultaViewModel
            )
        }

        composable(AppScreen.VerMascotas.route) {
            VerMascotasScreen(
                navController = navController,
                mascotaViewModel = mascotaViewModel,
                consultaViewModel = consultaViewModel
            )
        }
    }
}
