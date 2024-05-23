package com.utp.spring.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "ingreso")
@Data
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso", nullable = false)
    private Long id;
    private Date fecha_hora_ingreso;
    private Date fecha_hora_salida;
    private String observacion;
    private char estado;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

}
