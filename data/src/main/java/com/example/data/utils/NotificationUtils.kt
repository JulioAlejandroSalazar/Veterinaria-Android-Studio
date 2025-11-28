package com.example.data.utils

import com.example.data.model.Consulta
import com.example.data.model.EstadoConsulta
import com.example.data.model.Veterinario
import java.time.LocalDate

object NotificationUtils {

    fun enviarRecordatorios(consultas: List<Consulta>) {
        val hoy = LocalDate.now()
        val pendientes = consultas.filter { it.estado == EstadoConsulta.PENDIENTE && it.fecha.isBefore(hoy.plusDays(7)) }

        if (pendientes.isEmpty()) {
            println("No hay recordatorios pendientes.")
        } else {
            println("\n=== Recordatorios ===")
            pendientes.forEach {
                println("Recordatorio: consulta de ${it.mascota.nombre} con ${it.veterinario.nombre} en ${it.fecha}")
            }
        }
    }

    fun asignar(vet: Veterinario, consulta: Consulta): Boolean {
        return if (vet.disponible(consulta.hora, consulta.fecha)) {
            vet.asignarConsulta(consulta)
            true
        } else {
            println("El veterinario ${vet.nombre} no está disponible en esa fecha y hora.")
            false
        }
    }
}
