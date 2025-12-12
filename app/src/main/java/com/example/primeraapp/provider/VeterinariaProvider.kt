package com.example.primeraapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.MatrixCursor
import android.net.Uri
import com.example.data.DataModule

class VeterinariaProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.example.primeraapp.provider"
        val URI_MASCOTAS = Uri.parse("content://$AUTHORITY/mascotas")
        val URI_CONSULTAS = Uri.parse("content://$AUTHORITY/consultas")
    }

    override fun onCreate() = true

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): android.database.Cursor? {
        return when (uri.lastPathSegment) {
            "mascotas" -> {
                val cursor = MatrixCursor(arrayOf("id", "nombre", "especie", "edad"))
                val lista = (DataModule.mascotaRepository as? com.example.data.repository.MascotaRepositoryImpl)
                    ?.getAllSync() ?: emptyList()
                lista.forEachIndexed { index, m ->
                    cursor.addRow(arrayOf(index, m.nombre, m.especie, m.edad))
                }
                cursor
            }
            "consultas" -> {
                val cursor = MatrixCursor(arrayOf("id", "mascotaNombre", "veterinario", "motivo", "fecha"))
                val lista = (DataModule.consultaRepository as? com.example.data.repository.ConsultaRepositoryImpl)
                    ?.getAllSync() ?: emptyList()
                lista.forEachIndexed { index, c ->
                    cursor.addRow(arrayOf(
                        index,
                        c.mascota.nombre,
                        c.veterinario.nombre,
                        c.motivo,
                        c.fecha.toString()
                    ))
                }
                cursor
            }
            else -> null
        }
    }

    override fun getType(uri: Uri) = null
    override fun insert(uri: Uri, values: ContentValues?) = null
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?) = 0
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?) = 0
}
