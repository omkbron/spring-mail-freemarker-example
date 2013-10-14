package com.magnabyte.pedidonotifmail.model;

public enum TipoAccion {
	ALTA("Pedido Alta"), BAJA("Pedido Cancelado");
	
	private String descripcion;

	private TipoAccion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
