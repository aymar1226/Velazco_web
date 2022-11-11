package com.utp.spring.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idcliente")
        private Long id;
        private String nombre;
        private String apellido;
        private String direccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
