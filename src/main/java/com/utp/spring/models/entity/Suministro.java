package com.utp.spring.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "suministro")
@Data
public class Suministro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suministro", nullable = false)
    private Long id;
    private String nombre;
    private int stock;
    private double precio;
    private char estado;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
}
