package com.universidad.sistemapedidos.pedidos.application;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.universidad.sistemapedidos.pedidos.application.processors.ProcesadorPedidoEstandar;
import com.universidad.sistemapedidos.pedidos.application.processors.ProcesadorPedidoExpress;
import com.universidad.sistemapedidos.pedidos.application.processors.ProcesadorPedidoInternacional;
import com.universidad.sistemapedidos.pedidos.domain.ProcesadorPedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

class ProcesadorPedidoFactoryTest {
    private ProcesadorPedidoFactory factory;

    @BeforeEach
    void setUp() {
        List<ProcesadorPedido> procesadores = List.of(
                new ProcesadorPedidoEstandar(),
                new ProcesadorPedidoExpress(),
                new ProcesadorPedidoInternacional()
        );
        factory = new ProcesadorPedidoFactory(procesadores);
    }

    @Test
    void obtener_debeRetornarEstandar() {
        assertTrue(factory.obtener(TipoPedido.ESTANDAR) instanceof ProcesadorPedidoEstandar);
    }

    @Test
    void obtener_tipoInvalido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> factory.obtener(null));
    }
}