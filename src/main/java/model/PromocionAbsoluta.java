package model;

import java.util.List;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(int id, String name, int costo, String atracciones, List<Attraction> listaAtraccion) {
		super(id, name, atracciones, listaAtraccion);
		enlazarAtraccion();
		calcularTiempo();
		calcularCosto(costo);
	}
	
	@Override
	public void enlazarAtraccion() {
		String [] spliteoAtrac = this.atracciones.split(",");
		for(String atracID : spliteoAtrac) {
			int id = Integer.parseInt(atracID);
			for(Attraction atrac : this.listaAtraccion) {
				if(atrac.getId() == id) {
					listaInterna.add(atrac);
				}
			}
		}
	}
	public void calcularCosto(int costo) {
		this.costo = costo;
	}
	
	@Override
	public String toString() {
		List<Attraction> attraction = this.listaInterna;
		String atracIncluidas = "";
		for (Attraction atrac : attraction) {
			atracIncluidas += atrac.getName() + ", ";
			}
		String newline = System.lineSeparator();
		return "Promocion" + newline +
				"Nombre Del Pack= " + name + newline +
				"Atracciones Incluidas= " + atracIncluidas + newline +
				"Costo Del Pack= " + costo + newline +
				"Duracion= " + tiempo;
	}

	@Override
	public double getDuration() {
		// TODO Auto-generated method stub
		return tiempo;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	
	


}
