package com.utp.spring.models.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "auditorias")
@ToString
@EqualsAndHashCode
@Getter@Setter
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long idAuditoria;
    private String evento;
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fin;
    private String estado;


}
