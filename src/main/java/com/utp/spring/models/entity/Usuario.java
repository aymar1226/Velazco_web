package com.utp.spring.models.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
@ToString
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idusuario", nullable = false)
    private Long idusuario;

    @Getter @Setter @Column(name = "rol")
    private String rol;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "dni")
    private String dni;

    @Getter @Setter @Column(name = "correo")
    private String correo;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "direccion")
    private String direccion;

    @Getter @Setter @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;

    public Usuario() {
    }

    public Usuario(Long idusuario, String rol, String nombre, String apellido, String dni, String correo, String telefono, String password) {
        this.idusuario = idusuario;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
    }
}
