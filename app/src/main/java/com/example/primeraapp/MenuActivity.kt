package com.example.primeraapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text("Menu Activity", style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            startActivity(Intent(this@MenuActivity, MainActivity::class.java))
                        }
                    ) {
                        Text("Volver al Inicio")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            startService(Intent(this@MenuActivity, ReminderService::class.java))
                        }
                    ) {
                        Text("Iniciar Recordatorio")
                    }
                }
            }
        }
    }
}
