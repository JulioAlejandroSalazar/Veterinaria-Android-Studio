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
import com.example.primeraapp.ui.components.BaseScreen
import com.example.primeraapp.ui.theme.AppBackground
import com.example.primeraapp.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onGoToRegister: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


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

                TextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                val composition by rememberLottieComposition(
                    LottieCompositionSpec.Asset("Loader_cat.json")
                )

                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .height(180.dp)
                        .padding(bottom = 24.dp)
                )

                if (viewModel.loginError) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Correo o contraseña incorrectos",
                        color = Color.Red
                    )
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
                        viewModel.login(correo, password)
                    }
                ) {
                    Text("Iniciar sesión")
                }

                TextButton(onClick = { onGoToRegister() }) {
                    Text("Crear cuenta", color = Color.White)
                }
            }
        }
    }
}


