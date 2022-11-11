package com.utp.spring.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "productos")
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto", nullable = false)
    private Long idproducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "stock")
    private String stock;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Producto() {
    }

    public Producto(Long idproducto, String nombre, String descripcion, Double precio, String imagen, Categoria categoria) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public Producto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public Producto(String nombre, String descripcion, Double precio, String imagen, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public Producto(String descripcion) {
        this.descripcion = descripcion;
    }


}
