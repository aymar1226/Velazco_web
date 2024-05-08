package com.utp.spring.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "ordenes_detalles")
public class DetalleOrden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;
	private double cantidad;
	private double precio_venta;
	private double valor_venta;
	
	@ManyToOne
	@JoinColumn(name = "ordenID")
	private Orden orden;
	
	@ManyToOne
	@JoinColumn(name = "productoID")
	private Producto producto;
	
	public DetalleOrden() {
	
	}
	public DetalleOrden(Integer id, String nombre, double cantidad, double precio, double total) {
		super();
		this.id = id;
		this.descripcion = nombre;
		this.cantidad = cantidad;
		this.precio_venta = precio;
		this.valor_venta = total;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return descripcion;
	}
	public void setNombre(String nombre) {
		this.descripcion = nombre;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio_venta;
	}
	public void setPrecio(double precio) {
		this.precio_venta = precio;
	}
	public double getTotal() {
		return valor_venta;
	}
	public void setTotal(double total) {
		this.valor_venta = total;
	}
	
	
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	@Override
	public String toString() {
		return "DetalleOrden [id=" + id + ", nombre=" + descripcion + ", cantidad=" + cantidad + ", precio=" + precio_venta
				+ ", total=" + valor_venta + "]";
	}

}
