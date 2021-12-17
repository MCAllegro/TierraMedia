package model;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private double porcentajeDescuento;

	public PromocionPorcentual(int id, String name, double descuento, String atracciones, List<Attraction> listaAtraccion) {
		super(id, name, atracciones, listaAtraccion);
		this.porcentajeDescuento = descuento;
		enlazarAtraccion();
		calcularTiempo();
		calcularCosto();
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

	@Override
	public void calcularCosto() {
		for(Attraction atrac : this.listaInterna) {
			this.costo += atrac.getCost();
		}
		this.costo -= Math.round(this.costo * (this.porcentajeDescuento/100));
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
				"Duracion= " + tiempo ;
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
