package com.universidad.sistemapedidos.pedidos.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universidad.sistemapedidos.pedidos.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}