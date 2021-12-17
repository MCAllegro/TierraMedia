package services;

import java.util.List;


import model.Itinerario;

import model.Ofertable;
import model.User;


public class ItinerarioService {
	private Itinerario iti = new Itinerario();
	
	public List<Ofertable> getListaIti(User user){
		return iti.getListaItinerario(user.getId());
	}
}
