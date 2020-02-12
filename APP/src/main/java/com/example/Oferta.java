package com.example;

public class Oferta {
	private String precio;
	private String enlace;
	private String mercado;
	
	public Oferta() {
		  precio = null;
		  enlace= null;
		  mercado=null;  
	  }

	public String getPrecio() {
		return precio;
	}
	
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	public String getEnlace() {
		return enlace;
	}
	
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
}
