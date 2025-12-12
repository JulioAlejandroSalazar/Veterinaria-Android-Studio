package com.example.primeraapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {

            Log.e("BootReceiver", "El dispositivo ha arrancado")
            Toast.makeText(context, "Boot Completed recibido!", Toast.LENGTH_LONG).show()

        }
    }
}
