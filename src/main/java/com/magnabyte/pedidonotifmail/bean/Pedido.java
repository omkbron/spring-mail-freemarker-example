package com.magnabyte.pedidonotifmail.bean;

import java.util.Date;
import java.util.List;

public class Pedido {
	private int numero;
	private String status;
	private String pedidoCliente;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Date fechaHoraAlta;
	private String almacen;
	private Cliente cliente;
	private String user;
	private List<Producto> productos;

	public Pedido(int numPedido) {
		this.numero = numPedido;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPedidoCliente() {
		return pedidoCliente;
	}

	public void setPedidoCliente(String descripcion) {
		this.pedidoCliente = descripcion;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaHoraAlta() {
		return fechaHoraAlta;
	}

	public void setFechaHoraAlta(Date fechaHoraAlta) {
		this.fechaHoraAlta = fechaHoraAlta;
	}

	public String getAlmacen() {
		return almacen;
	}

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido [numero=");
		builder.append(numero);
		builder.append(", status=");
		builder.append(status);
		builder.append(", pedidoCliente=");
		builder.append(pedidoCliente);
		builder.append(", fechaPedido=");
		builder.append(fechaPedido);
		builder.append(", fechaEntrega=");
		builder.append(fechaEntrega);
		builder.append(", almacen=");
		builder.append(almacen);
		builder.append(", cliente=");
		builder.append(cliente);
		builder.append(", user=");
		builder.append(user);
		builder.append(", productos=");
		builder.append(productos);
		builder.append("]");
		return builder.toString();
	}

}
