package com.utp.spring.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalle_orden_compra")
@Data
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra", nullable = false)
    private Long id;
    private int cantidad;
    private double precioxunidad;
    private double total;
    private char estado;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "suministro_id")
    private Suministro suministro;

}
