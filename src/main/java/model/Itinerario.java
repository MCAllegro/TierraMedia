package model;

import java.util.ArrayList;
import java.util.List;

import persistence.AttractionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAOImpl;

public class Itinerario {
	
	private ItinerarioDAO itineDao = DAOFactory.getItinerarioDao();
	private PromocionDAO promoDao = DAOFactory.getPromocionesDao();
	private AttractionDAO atracDao = DAOFactory.getAttractionDAO();
	private List<Ofertable> listaItinerario = new ArrayList<Ofertable>();
	
	private ItinerarioDAOImpl itiDao = (ItinerarioDAOImpl) DAOFactory.getItinerarioDao();

	public void itinerarioComprarPromo(Integer userId, int promoIdint) {
		String promoIdS = Integer.toString(promoIdint);
		if (itiDao.checkearComprasPrevias(userId)) {
			String promo = itiDao.trearPromoID(userId);
			if (promo != null) {
				promo += "," + promoIdS;
			} else {
				promo = promoIdS;
			}
			itiDao.updatePromoId(userId, promo);
		} else {
			itiDao.insert(userId, promoIdS, null);
		}
	}

	public void itinerarioComprarAtrac(Integer userId, int atracIdint) {
		String atracIdS = Integer.toString(atracIdint);
		if (itiDao.checkearComprasPrevias(userId)) {
			String atrac = itiDao.trearAtracID(userId);
			if (atrac != null) {
				atrac += "," + atracIdS;
			} else {
				atrac = atracIdS;
			}
			itiDao.updateAtracId(userId, atrac);
		} else {
			itiDao.insert(userId, null, atracIdS);
		}
	}
	
	// los proximos 4 metodos estan feos pero era lo unico que se me ocurrio con el tiempo que habia
		public boolean comproPromo(Integer userId, int atracId) {
			if (itiDao.checkearComprasPrevias(userId)) {
				String promos = itiDao.trearPromoID(userId);
				if (promos != null) {
					String[] spliteoPromo = promos.split(",");
					return encontrarPromo(spliteoPromo, atracId);
				} else {
					return true;
				}
			}
			return true;
		}

		private boolean encontrarPromo(String[] spliteoPromo, int atracId) {
			for (Promocion promo : promoDao.findAll()) {
				for (String spliteoPromoId : spliteoPromo) {
					if (promo.getId() == Integer.parseInt(spliteoPromoId)) {
						for (Attraction listaItem : promo.getListaInterna()) {
							if(listaItem.getId() == atracId) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}
		
		public boolean comproAtrac(Integer userId, int promoId) {
			if(itiDao.checkearComprasPrevias(userId)) {
				String atracs = itiDao.trearAtracID(userId);
				if(atracs != null) {
					String [] spliteoAtrac = atracs.split(",");
					return encontrarAtrac(spliteoAtrac, promoId);
				} else {
					return true;
				}
			}
			return true;
		}
		
		private boolean encontrarAtrac(String[] spliteoAtrac, int promoId) {
			for(Promocion promo : promoDao.findAll()) {
				if(promo.getId() == promoId) {
					for(String spliteoAtracId : spliteoAtrac) {
						int atracId = Integer.parseInt(spliteoAtracId);
						for(Attraction listaItem : promo.listaInterna) {
							if(listaItem.getId() == atracId) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}
		public List<Ofertable> getListaItinerario(Integer userId) {
			String atracs = itiDao.trearAtracID(userId);
			String promos = itiDao.trearPromoID(userId);
			if (atracs != null) {
				String[] spliteoAtrac = atracs.split(",");
				for (String spliteoAtracId : spliteoAtrac) {
					for (Attraction atrac : atracDao.findAll()) {
						if (atrac.getId() == Integer.parseInt(spliteoAtracId)) {
							if(!listaItinerario.contains(atrac)) {
								this.listaItinerario.add(atrac);
								}
						}
					}
				}

			}
			if (promos != null) {
				String[] spliteoPromo = promos.split(",");
				for (String spliteoPromoId : spliteoPromo) {
					for (Promocion promo : promoDao.findAll()) {
						if (promo.getId() == Integer.parseInt(spliteoPromoId)) {
							if(!listaItinerario.contains(promo)) {
								this.listaItinerario.add(promo);
							}
						}
					}
				}
			}
			return listaItinerario;
		}
	}





