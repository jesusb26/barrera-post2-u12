package com.universidad.sistemapedidos.pedidos.application.processors;
import org.springframework.stereotype.Component;

import com.universidad.sistemapedidos.pedidos.domain.EstadoPedido;
import com.universidad.sistemapedidos.pedidos.domain.Pedido;
import com.universidad.sistemapedidos.pedidos.domain.ProcesadorPedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

@Component
public class ProcesadorPedidoExpress implements ProcesadorPedido {
    @Override
    public TipoPedido getTipo() { return TipoPedido.EXPRESS; }

    @Override
    public void procesar(Pedido pedido) {
        pedido.setCosto(pedido.getSubtotal() * 1.3);
        pedido.setEstado(EstadoPedido.PROCESADO);
    }
}
