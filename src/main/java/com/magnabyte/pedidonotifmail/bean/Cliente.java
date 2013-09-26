package com.magnabyte.pedidonotifmail.bean;

public class Cliente {
	private String clave;
	private String nombre;

	public Cliente() {
	}
	
	public Cliente(String clave, String nombre) {
		super();
		this.clave = clave;
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [clave=");
		builder.append(clave);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append("]");
		return builder.toString();
	}

}
