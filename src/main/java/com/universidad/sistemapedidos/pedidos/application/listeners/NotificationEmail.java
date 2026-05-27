package com.universidad.sistemapedidos.pedidos.application.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.universidad.sistemapedidos.pedidos.domain.PedidoProcesadoEvent;

@Component
public class NotificationEmail {
    private static final Logger log = LoggerFactory.getLogger(NotificationEmail.class);

    @EventListener
    public void handle(PedidoProcesadoEvent event) {
        log.info("[EMAIL] Notificación para pedido: {}", event.pedido().getId());
    }
}