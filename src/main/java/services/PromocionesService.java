package services;

import java.util.List;

import model.Promocion;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import persistence.impl.PromocionDAOImpl;

public class PromocionesService {
	private PromocionDAO promocionDao = DAOFactory.getPromocionesDao();
	
	public List<Promocion> getList(){
		return promocionDao.findAll();
		}
	}
