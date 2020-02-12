package com.example;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

@WebServlet(
    name = "SmartyPadel",
    urlPatterns = {"/smartypadel"}
)

public class SmartyPadel extends HttpServlet {
	
	public InterfazDatos scrapPZ = new ScrappingPZ();
	public InterfazDatos scrapT2P = new ScrapingT2P();
	public InterfazDatos scrapPS = new ScrappingPS();
	private ArrayList<Pala> Palas = new ArrayList<Pala>();
	
	
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException {
    Palas = new ArrayList<Pala>(); 
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    AmazonProducts amazon_products;
    ArrayList<String> datos_amazon = new ArrayList<String>();
    IntegracionDatos intDatos = new IntegracionDatos(Palas,scrapPZ.query(request.getParameter("query")), 10);
    intDatos.procesarDatosPZ(Palas, scrapPZ.query(request.getParameter("query")));
    
    for(int i=0; i< Palas.size(); i++) {
	    Pala p = Palas.get(i);
	    intDatos.procesarDatosT2P(p, scrapT2P.query(p.getNombre()));
	    intDatos.procesarDatosPS(p, scrapPS.query(p.getNombre()));
    }
    
    for(int i=0; i < Palas.size(); i++) {
    	amazon_products = new AmazonProducts(Palas.get(i).getNombre());
	    datos_amazon = null;
	    try {
			datos_amazon = amazon_products.getInformacion();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	    if(!datos_amazon.isEmpty())
	    	Palas.get(i).setOferta(datos_amazon, 0, "Amazon");
    }
    
    request.setAttribute("MatchedProducts", Palas);
    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    try {
		dispatcher.forward(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	}
  }
}