package com.utp.spring.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", nullable = false)
    private Long id;
    private char estado;


    @OneToOne(cascade = CascadeType.ALL) // Aquí agregamos CascadeType.ALL
    @JoinColumn(name = "persona_id")
    @JsonIgnoreProperties("empleado")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

}
