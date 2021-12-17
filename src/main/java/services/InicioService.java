package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import model.Attraction;
import model.Ofertable;

import model.Promocion;
import persistence.AttractionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

import utils.OfertableComparator;



public class InicioService {
	private AttractionDAO atraccionDao = DAOFactory.getAttractionDAO();
	private PromocionDAO promocionDao = DAOFactory.getPromocionesDao();
	private List<Ofertable> listaOfrecible = new ArrayList<Ofertable>();
	

	public List<Ofertable> getLista() {
		for(Attraction atrac : this.atraccionDao.findAll()) {
			if(!listaOfrecible.contains(atrac)) {
			this.listaOfrecible.add(atrac);
			}
		}
		
		for(Promocion promo : this.promocionDao.findAll()) {
			if(!listaOfrecible.contains(promo)) {
				this.listaOfrecible.add(promo);
			}
		}
		Collections.sort(listaOfrecible, new OfertableComparator());
		return this.listaOfrecible;
	}
}
