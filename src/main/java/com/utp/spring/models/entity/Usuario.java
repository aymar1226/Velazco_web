package com.utp.spring.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @Column(nullable=false)
    private String correo;

    @Column(name = "contraseña")
    private String password;
    private char estado;

//    @OneToMany(mappedBy = "usuario")
 //   private List<Orden> ordenes;

    @ManyToOne
    @JoinColumn(name = "privilegio_id")

    private Privilegio privilegio;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    @JsonIgnoreProperties("usuario")
    private Persona persona;


    @OneToOne(mappedBy = "usuario")
    @JsonManagedReference // Esta anotación indica que esta es la parte "administrada" de la relación
    private Carrito carrito;
}
