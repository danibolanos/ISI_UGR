package com.example;

public class ComparaCadenas {
	private String cadenaPZ;
	
	public ComparaCadenas(String cadena) {
		this.cadenaPZ = cadena;
	 }

	public String getCadenaPZ() {
		return this.cadenaPZ;
	}
	
	public int indCoincide(String cadenaOtra) {
		int indice = 0;
		Boolean sigue = true;
		String[] padelzoom = cadenaPZ.split(" ");
		String[] otra = cadenaOtra.split(" ");
		for(int i=0; i < padelzoom.length; i++) {
			sigue = true;
			for(int j=0; j < otra.length && sigue; j++) {
				if(padelzoom[i].equals(otra[j])) {
					sigue = false;
					indice++;
				}
			}
		}	
		if(Math.abs(padelzoom.length-otra.length) < indice)
			indice = 100;
		
		return indice;
	}
}
