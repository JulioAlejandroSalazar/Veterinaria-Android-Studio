package com.example.primeraapp.auth

import android.content.Context
import com.example.domain.model.Usuario

class AuthManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun register(usuario: Usuario): Boolean {
        if (prefs.contains("correo")) return false

        prefs.edit()
            .putString("nombre", usuario.nombre)
            .putString("telefono", usuario.telefono)
            .putString("correo", usuario.correo)
            .putString("password", usuario.password)
            .apply()

        return true
    }

    fun login(correo: String, password: String): Boolean {
        val savedCorreo = prefs.getString("correo", null)
        val savedPass = prefs.getString("password", null)

        return correo == savedCorreo && password == savedPass
    }

    fun isLogged(): Boolean =
        prefs.getBoolean("isLogged", false)

    fun setLogged(logged: Boolean) {
        prefs.edit().putBoolean("isLogged", logged).apply()
    }

    fun logout() {
        prefs.edit().putBoolean("isLogged", false).apply()
    }

    fun getUsuario(): Usuario? {
        val nombre = prefs.getString("nombre", null) ?: return null
        val telefono = prefs.getString("telefono", null) ?: return null
        val correo = prefs.getString("correo", null) ?: return null
        val password = prefs.getString("password", null) ?: return null

        return Usuario(nombre, telefono, correo, password)
    }
}
