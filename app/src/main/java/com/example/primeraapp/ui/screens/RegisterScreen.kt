package com.example.primeraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.domain.model.Usuario
import com.example.primeraapp.ui.components.BaseScreen
import com.example.primeraapp.ui.theme.AppBackground
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


    BaseScreen {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Registro de usuario",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                val composition by rememberLottieComposition(
                    LottieCompositionSpec.Asset("Loader_cat.json")
                )

                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.height(150.dp)
                )

                if (error != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = error!!, color = Color.Red)
                }

                if (success) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Registro exitoso",
                        color = Color(0xFF2E7D32)
                    )

                    LaunchedEffect(Unit) {
                        onRegisterSuccess()
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(),
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

                TextButton(onClick = onBackToLogin) {
                    Text("Volver al login", color = Color.White)
                }
            }
        }
    }
}