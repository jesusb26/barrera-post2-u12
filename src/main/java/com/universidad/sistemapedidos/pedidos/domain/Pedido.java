package com.universidad.sistemapedidos.pedidos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoPedido tipo;
    private double subtotal;
    private double costo;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    public Pedido() {}

    public Pedido(TipoPedido tipo, double subtotal) {
        this.tipo = tipo;
        this.subtotal = subtotal;
        this.estado = EstadoPedido.PENDIENTE;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TipoPedido getTipo() { return tipo; }
    public void setTipo(TipoPedido tipo) { this.tipo = tipo; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
}