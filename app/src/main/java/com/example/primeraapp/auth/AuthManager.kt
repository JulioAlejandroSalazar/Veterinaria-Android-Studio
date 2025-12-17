package com.example.primeraapp.auth

import android.content.Context
import com.example.domain.model.Usuario

class AuthManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun register(usuario: Usuario): Boolean {
        val userKey = "user_${usuario.correo}"

        if (prefs.contains(userKey)) return false

        prefs.edit()
            .putString("${userKey}_nombre", usuario.nombre)
            .putString("${userKey}_telefono", usuario.telefono)
            .putString("${userKey}_password", usuario.password)
            .putBoolean(userKey, true)
            .apply()

        return true
    }

    fun login(correo: String, password: String): Boolean {
        val userKey = "user_$correo"
        val savedPass = prefs.getString("${userKey}_password", null)
        return savedPass == password
    }

    fun setLogged(correo: String) {
        prefs.edit()
            .putBoolean("isLogged", true)
            .putString("currentUser", correo)
            .apply()
    }

    fun logout() {
        prefs.edit()
            .remove("isLogged")
            .remove("currentUser")
            .apply()
    }

    fun isLogged(): Boolean =
        prefs.getBoolean("isLogged", false)
}
