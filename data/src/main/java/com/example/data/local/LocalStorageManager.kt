package com.example.data.local

import android.content.Context

class LocalStorageManager(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("veterinaria_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_MASCOTA_NOMBRE = "mascota_nombre"
        private const val KEY_MASCOTA_ESPECIE = "mascota_especie"
        private const val KEY_MASCOTA_EDAD = "mascota_edad"
        private const val KEY_DUENO_NOMBRE = "dueno_nombre"
        private const val KEY_CONSULTA_MOTIVO = "consulta_motivo"
    }

    fun guardarConsultaActiva(
        mascotaNombre: String,
        mascotaEspecie: String,
        mascotaEdad: Int,
        duenoNombre: String,
        motivoConsulta: String
    ) {
        sharedPreferences.edit().apply {
            putString(KEY_MASCOTA_NOMBRE, mascotaNombre)
            putString(KEY_MASCOTA_ESPECIE, mascotaEspecie)
            putInt(KEY_MASCOTA_EDAD, mascotaEdad)
            putString(KEY_DUENO_NOMBRE, duenoNombre)
            putString(KEY_CONSULTA_MOTIVO, motivoConsulta)
            apply()
        }
    }

    fun obtenerConsultaActiva(): ConsultaActiva? {
        val nombreMascota = sharedPreferences.getString(KEY_MASCOTA_NOMBRE, null)
        val especie = sharedPreferences.getString(KEY_MASCOTA_ESPECIE, null)
        val edad = sharedPreferences.getInt(KEY_MASCOTA_EDAD, -1)
        val dueno = sharedPreferences.getString(KEY_DUENO_NOMBRE, null)
        val motivo = sharedPreferences.getString(KEY_CONSULTA_MOTIVO, null)

        return if (
            nombreMascota != null &&
            especie != null &&
            edad != -1 &&
            dueno != null &&
            motivo != null
        ) {
            ConsultaActiva(
                nombreMascota,
                especie,
                edad,
                dueno,
                motivo
            )
        } else {
            null
        }
    }

    fun limpiarConsultaActiva() {
        sharedPreferences.edit().clear().apply()
    }
}


data class ConsultaActiva(
    val mascotaNombre: String,
    val mascotaEspecie: String,
    val mascotaEdad: Int,
    val duenoNombre: String,
    val motivoConsulta: String
)