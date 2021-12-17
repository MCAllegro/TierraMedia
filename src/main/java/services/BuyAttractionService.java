package services;

import java.util.HashMap;
import java.util.Map;

import model.Attraction;
import model.Itinerario;
import model.User;
import persistence.AttractionDAO;

import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();
	private Itinerario itinerario = new Itinerario();

	public Map<String, String> buy(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Attraction attraction = attractionDAO.find(attractionId);

		if (!attraction.canHost()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(attraction);
			attraction.reservar();

			attractionDAO.updateCompra(attraction);
			userDAO.update(user);
			itinerario.itinerarioComprarAtrac(user.getId(), attraction.getId());
		}

		return errors;

	}

}

