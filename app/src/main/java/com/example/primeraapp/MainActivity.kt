package com.example.primeraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.data.repository.MascotaRepositoryImpl
import com.example.data.repository.ConsultaRepositoryImpl
import com.example.primeraapp.viewmodel.MascotaViewModel
import com.example.primeraapp.viewmodel.MascotaViewModelFactory
import com.example.primeraapp.viewmodel.ConsultaViewModel
import com.example.primeraapp.viewmodel.ConsultaViewModelFactory

class MainActivity : ComponentActivity() {

    private val mascotaRepository = MascotaRepositoryImpl()
    private val consultaRepository = ConsultaRepositoryImpl()

    private val mascotaViewModel: MascotaViewModel by viewModels {
        MascotaViewModelFactory(mascotaRepository)
    }
    private val consultaViewModel: ConsultaViewModel by viewModels {
        ConsultaViewModelFactory(consultaRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VeterinariaApp(
                mascotaViewModel = mascotaViewModel,
                consultaViewModel = consultaViewModel
            )
        }
    }
}
