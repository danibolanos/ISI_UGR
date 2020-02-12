package com.example;

import java.util.ArrayList;

public class Pala{
  private String nombre;
  private String imagen;
  private String puntuacion;
  private ArrayList<Oferta> ofertas;
  
  public String getNombre() {
    return nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getImagen() {
    return imagen;
  }
  
  public void setImagen(String imagen) {
    this.imagen = imagen;
  }
  
  public String getPuntuacion() {
    return puntuacion;
  }
  
  public void setPuntuacion(String puntuacion) {
    this.puntuacion = puntuacion;
  }

  public ArrayList<Oferta> getOfertas() {
	return ofertas;
  }

  public void setOfertas(ArrayList<Oferta> ofertas) {
	this.ofertas = ofertas;
  }
  
  public void setOferta(ArrayList<String> oferta, int i, String market) {
	 this.ofertas.get(i).setPrecio(oferta.get(2).substring(4));
	 this.ofertas.get(i).setEnlace(oferta.get(1));
	 this.ofertas.get(i).setMercado(market);
  }

  public void addOferta(ArrayList<String> datos, String market) {
	  Oferta oferta = new Oferta();
	  oferta.setMercado(market);
	  oferta.setEnlace(datos.get(1));
	  oferta.setPrecio(datos.get(2));
	  ofertas.add(oferta);
  }
  
}