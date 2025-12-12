package com.example.primeraapp

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.primeraapp.viewmodel.MascotaViewModel
import com.example.primeraapp.viewmodel.MascotaViewModelFactory
import com.example.primeraapp.viewmodel.ConsultaViewModel
import com.example.primeraapp.viewmodel.ConsultaViewModelFactory
import com.example.data.DataModule
import com.example.primeraapp.receivers.WifiStateReceiver

class MainActivity : ComponentActivity() {

    private val mascotaViewModel: MascotaViewModel by viewModels {
        MascotaViewModelFactory(DataModule.mascotaRepository)
    }

    private val consultaViewModel: ConsultaViewModel by viewModels {
        ConsultaViewModelFactory(DataModule.consultaRepository)
    }

    private val wifiReceiver = WifiStateReceiver()

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(wifiReceiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    VeterinariaApp(
                        mascotaViewModel = mascotaViewModel,
                        consultaViewModel = consultaViewModel
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = {
                        startActivity(Intent(this@MainActivity, MenuActivity::class.java))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Menú")
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = {
                        testProvider(context)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Probar ContentProvider")
                }
            }
        }
    }


    private fun testProvider(context: android.content.Context) {

        val sb = StringBuilder()

        sb.append("=== Mascotas ===\n")

        // Mascotas
        val cursorMascotas = contentResolver.query(
            Uri.parse("content://com.example.primeraapp.provider/mascotas"),
            null, null, null, null
        )

        cursorMascotas?.use {
            if (it.moveToFirst()) {
                do {
                    val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val especie = it.getString(it.getColumnIndexOrThrow("especie"))
                    val edad = it.getInt(it.getColumnIndexOrThrow("edad"))

                    sb.append("$nombre - $especie ($edad años)\n")
                } while (it.moveToNext())
            } else {
                sb.append("No hay mascotas.\n")
            }
        }

        sb.append("\n=== Consultas ===\n")

        // Consultas
        val cursorConsultas = contentResolver.query(
            Uri.parse("content://com.example.primeraapp.provider/consultas"),
            null, null, null, null
        )

        cursorConsultas?.use {
            if (it.moveToFirst()) {
                do {
                    val mascotaNombre = it.getString(it.getColumnIndexOrThrow("mascotaNombre"))
                    val veterinario = it.getString(it.getColumnIndexOrThrow("veterinario"))
                    val motivo = it.getString(it.getColumnIndexOrThrow("motivo"))
                    val fecha = it.getString(it.getColumnIndexOrThrow("fecha"))

                    sb.append("$mascotaNombre | $veterinario | $motivo | $fecha\n")
                } while (it.moveToNext())
            } else {
                sb.append("No hay consultas.\n")
            }
        }

        AlertDialog.Builder(context)
            .setTitle("ContentProvider")
            .setMessage(sb.toString())
            .setPositiveButton("OK", null)
            .show()
    }
}
