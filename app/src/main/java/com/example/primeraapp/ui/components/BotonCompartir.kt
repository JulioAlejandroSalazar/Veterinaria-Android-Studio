package com.example.primeraapp.ui.components

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.domain.model.Mascota

@Composable
fun BotonCompartir(mascota: Mascota) {
    val context = LocalContext.current

    Button(onClick = {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Mascota: ${mascota.nombre}\nTipo: ${mascota.especie}\nEdad: ${mascota.edad}"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Compartir mascota")
        context.startActivity(shareIntent)

    }) {
        Text("Compartir")
    }
}
