package com.utp.spring.models.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor", nullable = false)
    private Long id;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private char estado;


}
