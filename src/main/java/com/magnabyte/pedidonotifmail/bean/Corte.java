package com.magnabyte.pedidonotifmail.bean;

public class Corte {
	private int item;
	private int numero;
	private int cantidad;
	private int numBobinas;
	private double ancho;
	private double largo;

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getNumBobinas() {
		return numBobinas;
	}

	public void setNumBobinas(int numBobinas) {
		this.numBobinas = numBobinas;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Corte [numero=");
		builder.append(numero);
		builder.append(", cantidad=");
		builder.append(cantidad);
		builder.append(", numBobinas=");
		builder.append(numBobinas);
		builder.append(", ancho=");
		builder.append(ancho);
		builder.append(", largo=");
		builder.append(largo);
		builder.append("]");
		return builder.toString();
	}

}
