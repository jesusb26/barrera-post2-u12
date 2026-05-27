package com.universidad.sistemapedidos.pedidos.controllers;

import com.universidad.sistemapedidos.pedidos.domain.TipoPedido;

public class PedidoRequest {
    private TipoPedido tipo;
    private double subtotal;

    // getters y setters
    public TipoPedido getTipo() { return tipo; }
    public void setTipo(TipoPedido tipo) { this.tipo = tipo; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}