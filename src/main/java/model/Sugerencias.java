package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import utils.OfertableComparator;
import persistence.AttractionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;





public class Sugerencias {
	private AttractionDAO atraccionDao = DAOFactory.getAttractionDAO();
	private PromocionDAO promocionDao = DAOFactory.getPromocionesDao();
	private List<Ofertable> listaOfertable = new ArrayList<Ofertable>();
	

	public List<Ofertable> getSugerencias() {
		for(Attraction atrac : this.atraccionDao.findAll()) {
			if(!listaOfertable.contains(atrac)) {
			this.listaOfertable.add(atrac);
			}
		}
		
		for(Promocion promo : this.promocionDao.findAll()) {
			if(!listaOfertable.contains(promo)) {
				this.listaOfertable.add(promo);
			}
		}
		Collections.sort(this.listaOfertable, new OfertableComparator());
		return this.listaOfertable;
	}
}
