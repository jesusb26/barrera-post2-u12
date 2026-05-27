package com.universidad.sistemapedidos.pedidos;

import com.universidad.sistemapedidos.pedidos.domain.*;
import com.universidad.sistemapedidos.pedidos.application.FachadaPedidos;
import com.universidad.sistemapedidos.pedidos.application.listeners.NotificationEmail;
import com.universidad.sistemapedidos.pedidos.infrastructure.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class NotificationListenerIntegrationTest {

    @Autowired
    private FachadaPedidos fachada;

    @Autowired
    private PedidoRepository repository;

    @SpyBean
    private NotificationEmail notificationEmail; 

    @Test
    void crearPedido_debePublicarEventoYLlamarListener() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, 50.0);
        fachada.crearPedido(pedido);
        verify(notificationEmail).handle(any(PedidoProcesadoEvent.class));
    }
}