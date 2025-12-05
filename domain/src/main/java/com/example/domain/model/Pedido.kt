package com.example.domain.model

import kotlin.collections.iterator

class Pedido(
    val usuario: Usuario,
    private val items: MutableMap<Medicamento, Int> = mutableMapOf()
) {
    operator fun component1() = usuario
    operator fun component2() = items.toMap()
    operator fun component3() = calcularTotal()

    fun agregar(med: Medicamento, cantidad: Int) {
        if (cantidad <= 0) return
        items[med] = items.getOrDefault(med, 0) + cantidad
    }

    fun productos(): Map<Medicamento, Int> = items.toMap()

    fun calcularTotal(): Double {
        return items.entries.sumOf { (med, qty) -> med.precio * qty }
    }

    operator fun plus(other: Pedido): Pedido {
        val resultadoItems = mutableMapOf<Medicamento, Int>()
        for ((m, q) in this.items) resultadoItems[m] = resultadoItems.getOrDefault(m, 0) + q
        for ((m, q) in other.items) resultadoItems[m] = resultadoItems.getOrDefault(m, 0) + q
        return Pedido(this.usuario, resultadoItems)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Pedido(cliente=${usuario.nombre})\n")
        items.forEach { (med, qty) ->
            sb.append("  - ${med.nombre} ${med.dosificacion} x$qty -> ${med.precio * qty}\n")
        }
        sb.append("Total: ${calcularTotal()}")
        return sb.toString()
    }
}
