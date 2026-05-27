package com.universidad.sistemapedidos.pedidos.application;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.universidad.sistemapedidos.pedidos.domain.ProcesadorPedido;
import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

@Component
public class ProcesadorPedidoFactory {
    private final Map<TipoPedido, ProcesadorPedido> procesadores;

    public ProcesadorPedidoFactory(List<ProcesadorPedido> lista) {
        this.procesadores = lista.stream()
                .collect(Collectors.toMap(ProcesadorPedido::getTipo, Function.identity()));
    }

    public ProcesadorPedido obtener(TipoPedido tipo) {
        return Optional.ofNullable(procesadores.get(tipo))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de pedido no soportado: " + tipo));
    }
}