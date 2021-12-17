package services;

import model.Itinerario;
import model.Promocion;
import model.User;
import persistence.PromocionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;


public class ComprarPromoService {
	private PromocionDAO promoDao = DAOFactory.getPromocionesDao();
	private UserDAO userDao = DAOFactory.getUserDAO();
	private Itinerario itinerario = new Itinerario();
	
	public void comprarPromo(Integer userId, int promoId) {
		User user = userDao.find(userId);
		for(Promocion promo : promoDao.findAll()) {
			if(promoId == promo.getId()) {
				user.restarDineroTiempo(promo.getCost(), promo.getDuration());
				userDao.updateCompra(user);
				itinerario.itinerarioComprarPromo(user.getId(), promo.getId());
			}
		}

		
	}
}

