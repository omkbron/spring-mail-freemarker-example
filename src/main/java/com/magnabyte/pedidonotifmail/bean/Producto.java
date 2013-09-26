package com.magnabyte.pedidonotifmail.bean;

import java.util.List;

public class Producto {
	private int itemNum;
	private String clave;
	private String presentacion;
	private String descripcion;
	private double ancho;
	private double largo;
	private int cantidad;
	private String unidad;
	private List<Corte> cortes;
	private Pedido pedido;

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public List<Corte> getCortes() {
		return cortes;
	}

	public void setCortes(List<Corte> cortes) {
		this.cortes = cortes;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto [itemNum=");
		builder.append(itemNum);
		builder.append(", clave=");
		builder.append(clave);
		builder.append(", presentacion=");
		builder.append(presentacion);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", ancho=");
		builder.append(ancho);
		builder.append(", largo=");
		builder.append(largo);
		builder.append(", cantidad=");
		builder.append(cantidad);
		builder.append(", unidad=");
		builder.append(unidad);
		builder.append(", cortes=");
		builder.append(cortes);
		builder.append("]");
		return builder.toString();
	}

}
