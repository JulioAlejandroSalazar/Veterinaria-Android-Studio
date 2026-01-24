package com.example.primeraapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast

/*
class WifiStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == WifiManager.WIFI_STATE_CHANGED_ACTION) {

            val state = intent.getIntExtra(
                WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN
            )

            val message = when (state) {
                WifiManager.WIFI_STATE_ENABLED -> "WiFi activado"
                WifiManager.WIFI_STATE_DISABLED -> "WiFi desactivado"
                WifiManager.WIFI_STATE_ENABLING -> "Activando WiFi..."
                WifiManager.WIFI_STATE_DISABLING -> "Desactivando WiFi..."
                else -> "Estado WiFi desconocido"
            }

            Log.d("WifiStateReceiver", message)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
*/
