package persistence;

import model.Attraction;
import persistence.commons.GenericDAO;

public interface AttractionDAO extends GenericDAO<Attraction> {

	int erase(Integer id);

	int updateCompra(Attraction attraction);

}
