package com.example.domain.repository

import com.example.domain.model.Pedido
import kotlinx.coroutines.flow.Flow

interface PedidoRepository {

    fun getPedidosFlow(): Flow<List<Pedido>>

    suspend fun getAll(): List<Pedido>

    suspend fun findByUsuario(nombreUsuario: String): Pedido?

    suspend fun add(pedido: Pedido)

    suspend fun update(pedido: Pedido)

    suspend fun delete(nombreUsuario: String)
}
