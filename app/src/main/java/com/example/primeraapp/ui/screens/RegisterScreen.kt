package com.example.primeraapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.domain.model.Usuario
import com.example.primeraapp.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var error by remember { mutableStateOf<String?>(null) }
    var success by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Registro de usuario",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        TextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") })
        TextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            onClick = {
                if (nombre.isBlank() || telefono.isBlank() ||
                    correo.isBlank() || password.isBlank()
                ) {
                    error = "Completa todos los campos"
                    return@Button
                }

                val usuario = Usuario(
                    nombre = nombre,
                    telefono = telefono,
                    correo = correo,
                    password = password
                )

                val ok = viewModel.register(usuario)

                if (ok) {
                    success = true
                    error = null
                } else {
                    error = "Ya existe un usuario registrado"
                }

            }
        ) {
            Text("Registrarse")
        }

        if (error != null) {
            Text(
                text = error!!,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (success) {
            Text(
                text = "Registro exitoso",
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(top = 8.dp)
            )

            LaunchedEffect(Unit) {
                onRegisterSuccess()
            }
        }

        TextButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onBackToLogin
        ) {
            Text("Volver al login")
        }
    }
}