package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Promocion implements Ofertable{
	protected int id;
	protected String name;
	protected String atracciones;
	protected int costo = 0;
	protected double tiempo = 0;
	protected List<Attraction> listaAtraccion;
	protected List<Attraction> listaInterna = new ArrayList<Attraction>();
	
	
	public Promocion(int id, String name, String atracciones, List<Attraction> listaAtraccion) {
		this.id = id;
		this.name = name;
		this.atracciones = atracciones;
		this.listaAtraccion = listaAtraccion;
	}
	
	public abstract void enlazarAtraccion();
	
	public List<Attraction> getListaInterna(){
		return this.listaInterna;	
		};
	
	public void calcularTiempo() {
		for(Attraction atrac : this.listaInterna) {
			this.tiempo += atrac.getDuration();
		}
	}
	
	public void calcularCosto() {
		for(Attraction atrac : this.listaInterna) {
			this.costo += atrac.getCost();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getCost() {
		return costo;
	}
	
	public double getDuraction() {
		return tiempo;
	}
	
	public boolean esPromocion() {
		return true;
	}
	
	public void afirmarOfrecible() {}// solo atraccion usa este metodo

	public boolean esOfrecible() {
		Iterator<Attraction> itr = this.listaInterna.iterator();
		boolean ofrecer = true;
		while(itr.hasNext() && ofrecer == true) {
			ofrecer = itr.next().esOfrecible();
		}
		return ofrecer;
	}
	
	public boolean canHost() {
		Iterator<Attraction> itr = this.listaInterna.iterator();
		boolean cupo = true;
		while(itr.hasNext() && cupo == true) {
			cupo = itr.next().canHost();
		}
		return cupo;
	}

	public void reservar() {
		for(Attraction atrac : listaInterna) {
			atrac.reservar();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
