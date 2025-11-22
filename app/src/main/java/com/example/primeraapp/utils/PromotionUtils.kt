package com.example.primeraapp.utils

import com.example.primeraapp.data.model.Medicamento
import java.time.LocalDate

data class PromotionPeriod(val desde: LocalDate, val hasta: LocalDate, val descuento: Double) {
    fun estaEnPeriodo(fecha: LocalDate): Boolean {
        return fecha in desde..hasta
    }
}

object PromotionUtils {

    fun listarPromocionables(medicamentos: List<Medicamento>): List<Medicamento> {
        return medicamentos.filter { it.esPromocionable }
    }

    fun aplicarDescuento(medicamento: Medicamento, periodo: PromotionPeriod, fecha: LocalDate = LocalDate.now()): Double {
        return if (medicamento.esPromocionable && periodo.estaEnPeriodo(fecha)) {
            medicamento.precio * (1 - periodo.descuento)
        } else medicamento.precio
    }
}
