package com.example.primeraapp

import android.app.AlertDialog
import android.content.IntentFilter
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.data.DataModule
import com.example.primeraapp.auth.AuthManager
import com.example.primeraapp.receivers.WifiStateReceiver
import com.example.primeraapp.ui.screens.LoginScreen
import com.example.primeraapp.ui.screens.RegisterScreen
import com.example.primeraapp.viewmodel.*
import com.example.data.local.LocalStorageManager
import com.example.primeraapp.ui.components.ConsultaActivaView
import com.example.data.local.ConsultaActiva


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
        registerReceiver(
            wifiReceiver,
            IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        )
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
            val authViewModel = remember {
                AuthViewModel(context)
            }

            var showRegister by remember { mutableStateOf(false) }

            if (!authViewModel.isLogged) {

                if (showRegister) {
                    RegisterScreen(
                        viewModel = authViewModel,
                        onRegisterSuccess = {
                            showRegister = false
                        },
                        onBackToLogin = {
                            showRegister = false
                        }
                    )
                } else {
                    LoginScreen(
                        viewModel = authViewModel,
                        onGoToRegister = {
                            showRegister = true
                        }
                    )
                }

            } else {

                VeterinariaApp(
                    mascotaViewModel = mascotaViewModel,
                    consultaViewModel = consultaViewModel,
                    authViewModel = authViewModel
                )
            }
        }
    }

    fun testProvider(context: android.content.Context) {

        val sb = StringBuilder()
        sb.append("=== Mascotas ===\n")

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

        val cursorConsultas = contentResolver.query(
            Uri.parse("content://com.example.primeraapp.provider/consultas"),
            null, null, null, null
        )

        cursorConsultas?.use {
            if (it.moveToFirst()) {
                do {
                    val mascotaNombre =
                        it.getString(it.getColumnIndexOrThrow("mascotaNombre"))
                    val veterinario =
                        it.getString(it.getColumnIndexOrThrow("veterinario"))
                    val motivo =
                        it.getString(it.getColumnIndexOrThrow("motivo"))
                    val fecha =
                        it.getString(it.getColumnIndexOrThrow("fecha"))

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
