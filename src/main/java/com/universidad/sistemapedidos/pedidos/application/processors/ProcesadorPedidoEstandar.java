package com.universidad.sistemapedidos.pedidos.application.processors;

import org.springframework.stereotype.Component;

import com.universidad.sistemapedidos.pedidos.domain.EstadoPedido;
import com.universidad.sistemapedidos.pedidos.domain.Pedido;
import com.universidad.sistemapedidos.pedidos.domain.ProcesadorPedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

@Component
public class ProcesadorPedidoEstandar implements ProcesadorPedido {
    @Override
    public TipoPedido getTipo() { return TipoPedido.ESTANDAR; }

    @Override
    public void procesar(Pedido pedido) {
        pedido.setCosto(pedido.getSubtotal() * 1.1);
        pedido.setEstado(EstadoPedido.PROCESADO);
    }
}