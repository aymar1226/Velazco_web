package com.utp.spring.models.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo", nullable = false)
    private Long id;
    private String nombre;
    private char estado;


}
