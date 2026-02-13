package com.example.primeraapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.data.remote.VeterinarioImageClient
import com.example.primeraapp.ui.theme.AppBackground
import com.example.primeraapp.viewmodel.VeterinariosViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VeterinariosScreen(
    navController: NavController,
    viewModel: VeterinariosViewModel = viewModel()
) {

    val veterinarios by viewModel.veterinarios.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        containerColor = AppBackground,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nuestros Veterinarios",
                        modifier = Modifier.semantics {
                            contentDescription = "Título de la pantalla Nuestros Veterinarios"
                        }
                    )
                }
            )
        },

        bottomBar = {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Volver al Home")
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 16.dp)
        ) {

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    itemsIndexed(veterinarios) { index, vet ->

                        val imageUrl = VeterinarioImageClient.getImageUrl(index)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                GlideImage(
                                    model = imageUrl,
                                    contentDescription = "Foto veterinario",
                                    modifier = Modifier.size(80.dp)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column {
                                    Text(
                                        text = vet.nombre,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(text = vet.especialidad)

                                    Text(
                                        text = if (vet.disponible) "Disponible" else "No disponible",
                                        color = if (vet.disponible)
                                            Color(0xFF2E7D32)
                                        else
                                            Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

