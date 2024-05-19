package com.utp.spring.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.persistence.*;

@Entity
@Table(name = "privilegio")
@Data
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilegio", nullable = false)
    private Long id;
    private String nombre;
    private char estado;


}
