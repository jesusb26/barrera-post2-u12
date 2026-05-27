package com.universidad.sistemapedidos.pedidos.application.processors;
import org.springframework.stereotype.Component;

import com.universidad.sistemapedidos.pedidos.domain.EstadoPedido;
import com.universidad.sistemapedidos.pedidos.domain.Pedido;
import com.universidad.sistemapedidos.pedidos.domain.ProcesadorPedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

@Component
public class ProcesadorPedidoInternacional implements ProcesadorPedido {
    @Override
    public TipoPedido getTipo() { return TipoPedido.INTERNACIONAL; }

    @Override
    public void procesar(Pedido pedido) {
        pedido.setCosto(pedido.getSubtotal() * 1.5 + 25.0);
        pedido.setEstado(EstadoPedido.PROCESADO);
    }
}
