package com.example.data.repository

import com.example.domain.model.Pedido
import com.example.domain.repository.PedidoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PedidoRepositoryImpl : PedidoRepository {

    private val pedidos = mutableListOf<Pedido>()
    private val pedidosFlow = MutableStateFlow<List<Pedido>>(emptyList())

    override fun getPedidosFlow(): Flow<List<Pedido>> =
        pedidosFlow.asStateFlow()

    override suspend fun getAll(): List<Pedido> =
        pedidos.toList()

    override suspend fun findByUsuario(nombreUsuario: String): Pedido? =
        pedidos.find { it.usuario.nombre.equals(nombreUsuario, ignoreCase = true) }

    override suspend fun add(pedido: Pedido) {
        pedidos.add(pedido)
        emit()
    }

    override suspend fun update(pedido: Pedido) {
        val index = pedidos.indexOfFirst {
            it.usuario.nombre.equals(pedido.usuario.nombre, ignoreCase = true)
        }
        if (index != -1) {
            pedidos[index] = pedido
            emit()
        }
    }

    override suspend fun delete(nombreUsuario: String) {
        val removed = pedidos.removeIf {
            it.usuario.nombre.equals(nombreUsuario, ignoreCase = true)
        }
        if (removed) emit()
    }

    private fun emit() {
        pedidosFlow.value = pedidos.toList()
    }
}
