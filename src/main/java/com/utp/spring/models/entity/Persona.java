package com.utp.spring.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("persona")
    private Usuario usuario;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("persona")
    private Empleado empleado;


}
