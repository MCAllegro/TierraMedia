package persistence;


import model.Ofertable;
import persistence.commons.GenericDAO;

public interface ItinerarioDAO extends GenericDAO<Ofertable>{

	public String traerItinerario(int visitId);

	boolean checkearComprasPrevias(int userId);

	String trearPromoID(int visiID);

	String trearAtracID(int visiID);

}
