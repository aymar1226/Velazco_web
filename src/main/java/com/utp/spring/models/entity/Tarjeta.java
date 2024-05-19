package com.utp.spring.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarjeta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta", nullable = false)
    private Long id;
    private String titular;
    private String ciudad;
    private String num_tarjeta;
    private String año_exp;
    private String mes_exp;
    private String cvv;
    private char estado;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
