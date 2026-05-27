package com.universidad.sistemapedidos.pedidos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.sistemapedidos.pedidos.application.ServicioPedidosLegacy;
import com.universidad.sistemapedidos.pedidos.domain.Pedido;

@RestController
@RequestMapping("/api/legacy/pedidos")
public class PedidoControllerLegacy {

    @Autowired
    private ServicioPedidosLegacy servicio;

    @PostMapping
    public String procesar(@RequestBody Pedido pedido) {
        servicio.procesarPedido(pedido);
        return "Pedido procesado (legacy)";
    }
}