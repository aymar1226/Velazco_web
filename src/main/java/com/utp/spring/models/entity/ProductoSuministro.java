package com.utp.spring.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto_suministro")
@Data
public class ProductoSuministro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prodsuministro", nullable = false)
    private Long id;
    private double cantidad;
    private char estado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "suministro_id")
    private Suministro suministro;

}
