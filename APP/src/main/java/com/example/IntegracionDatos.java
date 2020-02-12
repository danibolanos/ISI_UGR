package com.example;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class IntegracionDatos {
  private Document doc;
  private int n_prod;
  
  public IntegracionDatos(ArrayList<Pala> Palas, String html, int num_prod) {
	  doc = Jsoup.parse(html);
	  n_prod = num_prod;
  }

  public Document getDoc(){
	  return this.doc;
	  
  }
  
  public int getNProd(){
    return this.n_prod;
  }
  
  public void procesarDatosPZ(ArrayList<Pala> Palas, String html_pz) {
	Document doc_pz = this.doc;
	Elements names_pz = doc_pz.select("h3");
	Elements links_pz = doc_pz.select("div.Pala-col > a");
	Elements prices_pz = doc_pz.select("p.Pala-col-precio");
	Elements score_pz = doc_pz.select("p.Pala-col-puntuacion");
	Elements imgs_pz = doc_pz.select("div.Pala-col  img");  
	ArrayList<String> nombres_pz = new ArrayList<String>();
	ArrayList<String> enlaces_pz = new ArrayList<String>();
	ArrayList<String> precios_pz = new ArrayList<String>();
	ArrayList<String> imagenes_pz = new ArrayList<String>();
	ArrayList<String> puntuacion_pz = new ArrayList<String>();

	for(Element e : names_pz){
      nombres_pz.add(e.text());

	}
	
	for(Element e : score_pz){
      puntuacion_pz.add(e.text());
	}
	
	for(Element e : imgs_pz){
      String aux = e.attr("data-lazy-src");
      imagenes_pz.add(aux);
	}
	
	for(Element l : links_pz){
		String aux =l.attr("href");
		enlaces_pz.add(aux);
	}
	
	for(Element p : prices_pz){
		precios_pz.add(p.text());
	}
	
	for(int i=0; i < names_pz.size() && i < getNProd(); i++){
		Pala aux = new Pala();
		Oferta oferta = new Oferta();
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		aux.setNombre(nombres_pz.get(i));
		aux.setImagen(imagenes_pz.get(i*2));
		aux.setPuntuacion(puntuacion_pz.get(i));
		oferta.setPrecio(precios_pz.get(i));
		oferta.setEnlace(enlaces_pz.get(i));
		oferta.setMercado("PadelZoom");
		ofertas.add(oferta);
		aux.setOfertas(ofertas);
		Palas.add(aux);
	}
  }
  
  public void procesarDatosT2P(Pala product, String html_cu) {
	Document doc_t2p = Jsoup.parse(html_cu);
	Elements links_t2p = doc_t2p.select("h4.name > a");
	Elements names_t2p = doc_t2p.select("h4.name");
	Elements prices_t2p = doc_t2p.select("span.product-price");
	ArrayList<String> ofertas = new ArrayList<String>();
	ComparaCadenas compara = new ComparaCadenas(product.getNombre());
	int maximo = 0;
	int indice = 0;
	String precio_t2p = "";
	String enlace_t2p = "";
	
	for(int i=0; i < names_t2p.size() && maximo < 3; i++){
		  int valor = compara.indCoincide(names_t2p.get(i).text());
	      if(valor > maximo) {
	    	  maximo = valor;
	    	  indice = i;
	      }
	}		
	
	if (prices_t2p.size() != 0 && maximo >= 3){
		enlace_t2p = links_t2p.get(indice).attr("href");
		precio_t2p = prices_t2p.get(indice).text();
		ofertas.add(null);
		ofertas.add(enlace_t2p);
		ofertas.add(precio_t2p);
		product.addOferta(ofertas, "Time2Padel");
	}
 }
  
  public void procesarDatosPS(Pala product, String html_cu) {
		Document doc_ps = Jsoup.parse(html_cu);
		Elements links_ps = doc_ps.select(" h3 > a");
		Elements prices_ps = doc_ps.select("span.price");
		Elements names_ps = doc_ps.select("h3.s_title_block");
		ArrayList<String> ofertas = new ArrayList<String>();
		ComparaCadenas compara = new ComparaCadenas(product.getNombre());
		int maximo = 0;
		int indice = 0;
		String precio_ps = "";
		String enlace_ps = "";
		
		for(int i=0; i < names_ps.size() && maximo < 3; i++){
			  int valor = compara.indCoincide(names_ps.get(i).text());
		      if(valor > maximo) {
		    	  maximo = valor;
		    	  indice = i;
		      }
		}		
		
		if (prices_ps.size() != 0 && maximo >= 3){
			enlace_ps = links_ps.get(indice).attr("href");
			precio_ps = prices_ps.get(indice).text();
			ofertas.add(null);
			ofertas.add(enlace_ps);
			ofertas.add(precio_ps);
			product.addOferta(ofertas, "PadelSuite");
		}
		
  }
  
}
