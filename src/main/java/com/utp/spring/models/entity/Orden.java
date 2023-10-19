package com.utp.spring.models.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Date fecha_creacion;
	private Date fecha_recibida;

	private double total;
	
	@ManyToOne
	@JoinColumn(name = "usuarioID")
	private Usuario usuario;


	
	@OneToMany(mappedBy = "orden")
	private List<DetalleOrden> detalle;
	
	public Orden() {
	
	}

	public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha_creacion = fechaCreacion;
		this.fecha_recibida = fechaRecibida;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCreacion() {
		return fecha_creacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fecha_creacion = fechaCreacion;
	}

	public Date getFechaRecibida() {
		return fecha_recibida;
	}

	public void setFechaRecibida(Date fechaRecibida) {
		this.fecha_recibida = fechaRecibida;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public List<DetalleOrden> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleOrden> detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Orden [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fecha_creacion + ", fechaRecibida="
				+ fecha_recibida + ", total=" + total + "]";
	}
	

}
