package model;

import java.util.ArrayList;
import java.util.List;

public class PromocionAxB extends Promocion {
	private String atraccionesGratis;
	
	public PromocionAxB(int id, String name, String atracciones, String atraccionesGratis, List<Attraction> listaAtraccion) {
		super(id, name, atracciones, listaAtraccion);
		this.atraccionesGratis = atraccionesGratis;
		enlazarAtraccion();
		calcularTiempo();
		calcularCosto();
	}
	
	@Override
	public void enlazarAtraccion() {
		String [] spliteoAtrac = this.atracciones.split(",");
		String [] spliteoAtracGratis = this.atraccionesGratis.split(",");
		for(Integer atracID : this.unirEnLista(spliteoAtrac, spliteoAtracGratis)) {
			int id = Integer.valueOf(atracID);
			for(Attraction atrac : this.listaAtraccion) {
				if(atrac.getId() == id) {
					listaInterna.add(atrac);
				}
			}
		}
	}
	
	private List<Integer> unirEnLista(String [] atrac, String [] atracGratis){
		List<Integer> listaID = new ArrayList<Integer>();
		for(String atracID : atrac) {
			int id = Integer.parseInt(atracID);
			listaID.add(id);
		}
		for(String atracGratisID : atracGratis) {
			int gratisId = Integer.parseInt(atracGratisID);
			listaID.add(gratisId);
		}
		return listaID;
	}

	@Override
	public void calcularCosto() {
		String [] spliteoAtrac = this.atracciones.split(",");
		for(String atracID : spliteoAtrac) {
			int id = Integer.parseInt(atracID);
			for(Attraction atrac : this.listaInterna) {
				if(id == atrac.getId()) {
					this.costo += atrac.getCost();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		List<Attraction> atraccion = this.listaInterna;
		String atracIncluidas = "";
		for (Attraction atrac : atraccion) {
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
