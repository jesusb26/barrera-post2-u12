package com.universidad.sistemapedidos.pedidos.domain;

public interface ProcesadorPedido {
    TipoPedido getTipo();
    void procesar(Pedido pedido);
}