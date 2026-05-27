package com.universidad.sistemapedidos.pedidos.application.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.universidad.sistemapedidos.pedidos.domain.PedidoProcesadoEvent;

@Component
public class NotificationLog {
    private static final Logger log = LoggerFactory.getLogger(NotificationLog.class);

    @EventListener
    public void handle(PedidoProcesadoEvent event) {
        log.info("Pedido procesado: ID={}, Costo={}", event.pedido().getId(), event.pedido().getCosto());
    }
}