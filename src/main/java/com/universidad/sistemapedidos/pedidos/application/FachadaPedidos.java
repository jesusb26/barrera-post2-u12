package com.universidad.sistemapedidos.pedidos.application;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.universidad.sistemapedidos.pedidos.domain.Pedido;
import com.universidad.sistemapedidos.pedidos.domain.PedidoProcesadoEvent;
import com.universidad.sistemapedidos.pedidos.infrastructure.PedidoRepository;

@Service
public class FachadaPedidos {
    private final ProcesadorPedidoFactory factory;
    private final PedidoRepository repository;
    private final ApplicationEventPublisher publisher;

    public FachadaPedidos(ProcesadorPedidoFactory factory, PedidoRepository repository,
                          ApplicationEventPublisher publisher) {
        this.factory = factory;
        this.repository = repository;
        this.publisher = publisher;
    }

    public Pedido crearPedido(Pedido pedido) {
        // Obtener la estrategia según el tipo y procesar
        factory.obtener(pedido.getTipo()).procesar(pedido);
        // Persistir
        Pedido guardado = repository.save(pedido);
        // Publicar evento (Observer)
        publisher.publishEvent(new PedidoProcesadoEvent(guardado));
        return guardado;
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }
}