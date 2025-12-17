package com.example.primeraapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.ui.components.BotonVolverHome
import com.example.primeraapp.ui.navigation.AppScreen
import com.example.primeraapp.viewmodel.ConsultaViewModel
import com.example.primeraapp.viewmodel.MascotaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerMascotasScreen(
    navController: NavHostController,
    mascotaViewModel: MascotaViewModel,
    consultaViewModel: ConsultaViewModel
) {
    val mascotas = mascotaViewModel.uiState.collectAsState().value.mascotas

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mascotas Registradas") }) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(mascotas) { mascota ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Text("Nombre: ${mascota.nombre}")
                            Text("Especie: ${mascota.especie}")
                            Text("Dueño: ${mascota.dueno.nombre}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                TextButton(
                                    onClick = {
                                        navController.navigate(
                                            AppScreen.RegistrarMascota.createRoute(mascota.id)
                                        )
                                    }
                                ) {
                                    Text("Editar")
                                }

                                TextButton(
                                    onClick = {
                                        mascotaViewModel.eliminarMascota(
                                            mascota.id
                                        )

                                        consultaViewModel.eliminarConsultasDeMascota(
                                            mascota.id
                                        )
                                    }
                                ) {
                                    Text(
                                        "Eliminar",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            BotonVolverHome(navController)
        }
    }

}
