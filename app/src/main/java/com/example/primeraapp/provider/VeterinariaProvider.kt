package com.example.primeraapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.MatrixCursor
import android.net.Uri
import com.example.data.DataModule
import com.example.data.repository.ConsultaRepositoryImpl
import com.example.data.repository.MascotaRepositoryImpl

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
                val lista =
                    (DataModule.mascotaRepository as? MascotaRepositoryImpl)
                        ?.getAllSync() ?: emptyList()

                lista.forEach { m ->
                    cursor.addRow(
                        arrayOf(
                            m.id,
                            m.nombre,
                            m.especie,
                            m.edad
                        )
                    )
                }
                cursor
            }

            "consultas" -> {
                val cursor = MatrixCursor(
                    arrayOf("id", "mascotaNombre", "veterinario", "motivo", "fecha")
                )

                val lista =
                    (DataModule.consultaRepository as? ConsultaRepositoryImpl)
                        ?.getAllSync() ?: emptyList()

                lista.forEach { c ->
                    cursor.addRow(
                        arrayOf(
                            c.id,
                            c.mascota.nombre,
                            c.veterinario.nombre,
                            c.motivo,
                            c.fecha.toString()
                        )
                    )
                }
                cursor
            }

            else -> null
        }
    }

    override fun getType(uri: Uri) = null
    override fun insert(uri: Uri, values: ContentValues?) = null
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ) = 0

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ) = 0
}
