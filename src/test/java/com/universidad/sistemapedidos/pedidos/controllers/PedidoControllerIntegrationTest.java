package com.universidad.sistemapedidos.pedidos.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.universidad.sistemapedidos.pedidos.domain.EstadoPedido;
import com.universidad.sistemapedidos.pedidos.domain.Pedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PedidoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void crearPedido_endpointRetornaPedidoProcesado() {
        Pedido pedido = new Pedido(TipoPedido.EXPRESS, 200.0);
        ResponseEntity<Pedido> response = restTemplate.postForEntity("/api/pedidos", pedido, Pedido.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        Pedido resultado = response.getBody();
        assertThat(resultado.getCosto()).isEqualTo(260.0); // 200 * 1.3
        assertThat(resultado.getEstado()).isEqualTo(EstadoPedido.PROCESADO);
    }
}