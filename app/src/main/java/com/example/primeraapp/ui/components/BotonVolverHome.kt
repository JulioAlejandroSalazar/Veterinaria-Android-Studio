package com.example.primeraapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeraapp.data.navigation.AppScreen

@Composable
fun BotonVolverHome(navController: NavHostController) {
    Button(
        onClick = { navController.navigate(AppScreen.Home.route) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Text("Volver al inicio")
    }
}
