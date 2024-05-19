package com.utp.spring.models.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private String stock;
    private char estado;



    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
