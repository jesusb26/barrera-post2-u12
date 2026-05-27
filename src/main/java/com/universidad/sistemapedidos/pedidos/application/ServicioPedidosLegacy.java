package com.universidad.sistemapedidos.pedidos.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.sistemapedidos.pedidos.domain.EstadoPedido;
import com.universidad.sistemapedidos.pedidos.domain.Pedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;
import com.universidad.sistemapedidos.pedidos.infrastructure.PedidoRepository;

@Service
public class ServicioPedidosLegacy {

    @Autowired
    private PedidoRepository repo;

    // Simulación de envío de email (para evitar dependencia real)
    private void enviarNotificacion(Pedido pedido) {
        System.out.println("Notificación enviada para pedido: " + pedido.getId());
    }

    public void procesarPedido(Pedido pedido) {
        // Lógica mezclada: cálculo según tipo
        if (pedido.getTipo() == TipoPedido.ESTANDAR) {
            pedido.setCosto(pedido.getSubtotal() * 1.1);
        } else if (pedido.getTipo() == TipoPedido.EXPRESS) {
            pedido.setCosto(pedido.getSubtotal() * 1.3);
        } else if (pedido.getTipo() == TipoPedido.INTERNACIONAL) {
            pedido.setCosto(pedido.getSubtotal() * 1.5 + 25.0);
        }
        pedido.setEstado(EstadoPedido.PROCESADO);
        repo.save(pedido);
        // Notificación acoplada directamente
        enviarNotificacion(pedido);
    }
}