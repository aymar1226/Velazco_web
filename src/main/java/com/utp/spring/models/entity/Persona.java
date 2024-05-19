package com.utp.spring.models.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Long id;
    private String documento;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String telefono;
    private String direccion;
    private char estado;

    @OneToOne(mappedBy = "persona")
    private Usuario usuario;

}
