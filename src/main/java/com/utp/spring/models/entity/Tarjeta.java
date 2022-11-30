package com.utp.spring.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "tarjetas")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titular;
    private String ciudad;

    private String nro_tarjeta;

    private String mes_exp;

    private String año_exp;

    private String cvv;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public Tarjeta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNro_tarjeta() {
        return nro_tarjeta;
    }

    public void setNro_tarjeta(String nro_tarjeta) {
        this.nro_tarjeta = nro_tarjeta;
    }

    public String getMes_exp() {
        return mes_exp;
    }

    public void setMes_exp(String mes_exp) {
        this.mes_exp = mes_exp;
    }

    public String getAño_exp() {
        return año_exp;
    }

    public void setAño_exp(String año_exp) {
        this.año_exp = año_exp;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
