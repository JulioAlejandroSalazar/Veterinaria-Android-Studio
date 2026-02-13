package com.example.data.repository

import android.util.Log
import com.example.data.DataModule
import com.example.data.remote.RetrofitInstance
import com.example.domain.model.Veterinario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class VeterinarioRepositoryImpl {

    suspend fun getVeterinarios(): List<Veterinario> {
        return withContext(Dispatchers.IO) {
            try {
                delay(1500) // simula llamada de red

                DataModule.veterinarios.mapIndexed { index, vet ->
                    Veterinario(
                        nombre = vet.nombre,
                        especialidad = vet.especialidad,
                        disponible = vet.disponible
                    )
                }

            } catch (e: Exception) {
                Log.e("VeterinarioRepo", "Error simulando carga", e)
                emptyList()
            }
        }
    }
}
