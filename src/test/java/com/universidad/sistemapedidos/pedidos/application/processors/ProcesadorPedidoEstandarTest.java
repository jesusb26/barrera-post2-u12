package com.universidad.sistemapedidos.pedidos.application.processors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.universidad.sistemapedidos.pedidos.domain.EstadoPedido;
import com.universidad.sistemapedidos.pedidos.domain.Pedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

class ProcesadorPedidoEstandarTest {
    @Test
    void procesar_debeCalcularCostoCorrectamente() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, 100.0);
        ProcesadorPedidoEstandar processor = new ProcesadorPedidoEstandar();
        processor.procesar(pedido);
        assertEquals(110.0, pedido.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }
}