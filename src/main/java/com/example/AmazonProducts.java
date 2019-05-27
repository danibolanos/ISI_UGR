package com.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AmazonProducts {
	private String salida_amazon;
	
	public AmazonProducts(String query) {
		AmazonAPI amazon = new AmazonAPI();
		String words[] = query.split(" ");
		if(words.length > 1) {
			words[0] += " " + words[1];
			if(words.length > 2) {
				words[0] += " " + words[2];
			}
			if(words.length > 3) {
				words[0] += " " + words[3];
			}
		}
	    salida_amazon = amazon.query(words[0]);
	}
	
	public ArrayList<String> getInformacion() throws SAXException, IOException, ParserConfigurationException 
	{
		InputStream in = new ByteArrayInputStream(salida_amazon.getBytes("utf-8"));
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
        ArrayList<String> salida = new ArrayList<String>();

        // process
        NodeList urls = doc.getElementsByTagName("DetailPageURL");
        NodeList prices = doc.getElementsByTagName("LowestNewPrice");   
        Node url = urls.item(0);
        Element e_url = (Element) url;
        Node price = prices.item(0);//.getLastChild();
        
        Element e_price = (Element) price;
        
        if(e_price!=null && e_url!=null) {
        	salida.add("Id desconocido");
        	salida.add(e_url.getTextContent());
        	/*System.out.println(e_price.getTextContent()+"\n");
           	System.out.println(e_price.getAttribute("Price")+"\n");
           	System.out.println(e_price.toString()+"\n");
           	System.out.println("\n");*/
        	salida.add(e_price.getTextContent().substring(7)+"€");


        }
        return salida;
	}

}
