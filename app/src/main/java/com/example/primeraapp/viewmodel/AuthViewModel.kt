package com.example.primeraapp.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.Usuario
import com.example.primeraapp.auth.AuthManager

class AuthViewModel(context: Context) : ViewModel() {

    private val authManager = AuthManager(context)

    var isLogged by mutableStateOf(authManager.isLogged())
        private set

    var loginError by mutableStateOf(false)
        private set

    fun login(correo: String, password: String) {
        loginError = false

        if (authManager.login(correo, password)) {
            authManager.setLogged(correo)
            isLogged = true
        } else {
            loginError = true
        }
    }

    fun register(usuario: Usuario): Boolean {
        return authManager.register(usuario)
    }

    fun logout() {
        authManager.logout()
        isLogged = false
    }
}

